package com.zhihao.platform.util;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.security.KeyPair;  
import java.security.KeyPairGenerator;  
import java.security.NoSuchAlgorithmException;  
import java.security.PrivateKey;  
import java.security.PublicKey;  
import java.security.interfaces.RSAPrivateKey;  
import java.security.interfaces.RSAPublicKey;  
  
import javax.crypto.Cipher;  
  
import sun.security.rsa.RSAPrivateKeyImpl;  
import sun.security.rsa.RSAPublicKeyImpl;  
import sun.security.util.DerValue;  
  
public class RSAEncrypt  
{  
  
    private static String keyPath1 = "D:\\PRIVATE_KEY.txt"; // 私钥文件地址  
    private static String keyPath2 = "D:\\PUBLIC_KEY.txt"; // 公钥文件地址  
  
    private KeyPairGenerator keyPairGen;  
  
    private KeyPair keyPair;  
  
    private RSAPrivateKey privateKey;  
  
    private RSAPublicKey publicKey;  
  
    public void createKey()  
    {  
        try  
        {  
            keyPairGen = KeyPairGenerator.getInstance("RSA");  
            keyPairGen.initialize(512);  
            keyPair = keyPairGen.generateKeyPair();  
            // Generate keys  
            privateKey = (RSAPrivateKey)keyPair.getPrivate();  
            publicKey = (RSAPublicKey)keyPair.getPublic();  
  
            byte[] privateEncode = privateKey.getEncoded();  
            try  
            {  
                // 生成私钥文件  
                createFile(privateEncode, keyPath1, true);  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
  
            byte[] publicEncode = publicKey.getEncoded();  
            try  
            {  
                // 生成公钥文件  
                createFile(publicEncode, keyPath2, true);  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }  
        catch (NoSuchAlgorithmException e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    public static String createFile(byte[] desEncode, String keyPath, boolean overWrite) throws IOException  
    {  
        // 指定文件是否存在  
        boolean fileExisted = false;  
  
        // 判断指定路径合法与否  
        if ((keyPath.indexOf(":\\") > 0) || (keyPath.indexOf(":/") > 0))  
        {  
            //  
        }  
        else  
        {  
            System.out.println("密钥文件存放位置不合法，" + keyPath);  
            return "";  
        }  
  
        // 判断密钥文件是否存在及可写  
        java.io.File keyFile = new File(keyPath);  
        fileExisted = keyFile.exists();  
        if (fileExisted && ((overWrite == false) || (keyFile.canWrite() == false)))  
        {  
            System.out.println("指定密钥文件" + keyFile + "已存在且不可写，请重新指定其他文件或修改覆盖选项为真。");  
            return "";  
        }  
  
        // 若密钥文件不存在，创建新文件  
        if (fileExisted == false)  
        {  
            if (keyFile.createNewFile() == false)  
            {  
                System.out.println("创建新文件" + keyFile + "失败。");  
                return "";  
            }  
        }  
  
        // 初始化IO对象，写入密钥  
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(keyPath));  
        try  
        {  
            out.writeObject(desEncode);  
        }  
        catch (IOException ex)  
        {  
            // 若使用用户指定位置存放失败，则使用服务器路径存放。  
            return "";  
        }  
        finally  
        {  
            out.close();  
        }  
        return "";  
    }  
  
    /** 
     * 得到私钥 
     *  
     * @param keyPath 
     *            私钥地址 
     * @return 
     */  
    public RSAPrivateKey getPrivateKey(String keyPath)  
    {  
        try  
        {  
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream(keyPath));  
            try  
            {  
                byte[] desEncodeRead = (byte[])in.readObject();  
                in.close();  
                DerValue d = new DerValue(desEncodeRead);  
  
                PrivateKey p = RSAPrivateKeyImpl.parseKey(d);  
                return (RSAPrivateKey)p;  
            }  
            catch (ClassNotFoundException ex1)  
            {  
                ex1.printStackTrace();  
            }  
            catch (IOException ex2)  
            {  
                ex2.printStackTrace();  
            }  
  
            return null;  
  
        }  
        catch (IOException ex)  
        {  
            ex.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 得到公钥 
     *  
     * @param keyPath 
     *            公钥文件地址 
     * @return 
     */  
    public RSAPublicKey getPublicKey(String keyPath)  
    {  
        try  
        {  
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(new java.io.FileInputStream(keyPath));  
            try  
            {  
                byte[] desEncodeRead = (byte[])in.readObject();  
                in.close();  
                DerValue d = new DerValue(desEncodeRead);  
  
                PublicKey p = RSAPublicKeyImpl.parse(d);  
                return (RSAPublicKey)p;  
            }  
            catch (ClassNotFoundException ex1)  
            {  
                ex1.printStackTrace();  
            }  
            catch (IOException ex2)  
            {  
                ex2.printStackTrace();  
            }  
  
            return null;  
  
        }  
        catch (IOException ex)  
        {  
            ex.printStackTrace();  
            return null;  
        }  
    }  
  
    public void encryptFile(RSAPublicKey publicKey, File file, File newFile)  
    {  
        try  
        {  
            InputStream is = new FileInputStream(file);  
            OutputStream os = new FileOutputStream(newFile);  
  
            byte[] bytes = new byte[53];  
            while (is.read(bytes) > 0)  
            {  
                byte[] e = this.encrypt(publicKey, bytes);  
                bytes = new byte[53];  
                os.write(e, 0, e.length);  
            }  
            os.close();  
            is.close();  
            System.out.println("write success");  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    public void decryptFile(RSAPrivateKey privateKey, File file, File newFile)  
    {  
        try  
        {  
            InputStream is = new FileInputStream(file);  
            OutputStream os = new FileOutputStream(newFile);  
            byte[] bytes1 = new byte[64];  
            while (is.read(bytes1) > 0)  
            {  
                byte[] de = this.decrypt(privateKey, bytes1);  
                bytes1 = new byte[64];  
                os.write(de, 0, de.length);  
            }  
            os.close();  
            is.close();  
            System.out.println("write success");  
  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
    }  
  
    /** */  
    /** 
     * * Encrypt String. * 
     *  
     * @return byte[] 
     */  
    protected byte[] encrypt(RSAPublicKey publicKey, byte[] obj)  
    {  
        if (publicKey != null)  
        {  
            try  
            {  
                Cipher cipher = Cipher.getInstance("RSA");  
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
                return cipher.doFinal(obj);  
            }  
            catch (Exception e)  
            {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
  
    /** */  
    /** 
     * * Basic decrypt method * 
     *  
     * @return byte[] 
     */  
    protected byte[] decrypt(RSAPrivateKey privateKey, byte[] obj)  
    {  
        if (privateKey != null)  
        {  
            try  
            {  
                Cipher cipher = Cipher.getInstance("RSA");  
                cipher.init(Cipher.DECRYPT_MODE, privateKey);  
                return cipher.doFinal(obj);  
            }  
            catch (Exception e)  
            {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
  
    public static void main(String[] args)  
    {  
        RSAEncrypt encrypt = new RSAEncrypt();  
      
        // 需要加密的文件  
        File file = new File("D:\\a.rar");  
        // 加密到哪个文件  
        File newFile = new File("D:\\b.rar");  
      
        encrypt.createKey();  
      
        RSAPublicKey publicKey = encrypt.getPublicKey(keyPath2);  
      
        // 公钥加密操作  
        encrypt.encryptFile(publicKey, file, newFile);  
      
        RSAPrivateKey privateKey = encrypt.getPrivateKey(keyPath1);  
      
        // 需要解密的文件  
        File file1 = new File("D:\\b.rar");  
        // 解密到哪个文件  
        File newFile1 = new File("D:\\c.rar");  
        // 私钥解密操作  
        encrypt.decryptFile(privateKey, file1, newFile1);  
    }  
} 