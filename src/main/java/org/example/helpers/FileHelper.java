package org.example.helpers;

import org.example.utils.LogUtils;

import java.io.File;
import java.text.MessageFormat;

public class FileHelper {
//    private static final String DRIVER_FOLDER = "driver\\132.0.6834.159";
    private static final String DRIVER_FOLDER = "driver\\129.0.6668.100";

    //Update code
    private static final String USER_DIR = "user.dir";
    //Update code
    private static final String USER_HOME = "user.home";
//    private static final String OBJECT_REPO_FOLDER = "demo-automation-test";
//    private static final String DATA_FOLDER = "src\\test\\resources\\datatest\\Audio";
    private static final String SRC_FOLDER = "src";
    private static final String TEST_FOLDER = "test";
    private static final String RESOURCES_FOLDER = "resources";
    private static final String DATA_FOLDER = "datatest";
//    private static final String DATA_AUDIO_FOLDER = "Audio";
//    private static final String DATA_DOCUMENT_FOLDER = "Document";
    private static final String DOWNLOAD_FOLDER = "Downloads";


    

    public static String getCorrectRepoJsonfilePath(String jsonFilePath) {
        String correctFilePath = System.getProperty(USER_DIR) + File.separator + TEST_FOLDER + File.separator + jsonFilePath;
        return correctFilePath;
    }

    public static String getCorrectDataFilePath(String filePath) {
//        String correctFilePath = System.getProperty(USER_DIR) + File.separator +  DATA_FOLDER + File.separator + filePath;
        String correctFilePath = System.getProperty(USER_DIR) +
                File.separator + SRC_FOLDER +
                File.separator + TEST_FOLDER +
                File.separator + RESOURCES_FOLDER+
                File.separator + DATA_FOLDER+
//                File.separator + DATA_AUDIO_FOLDER+
                File.separator +filePath;
        return correctFilePath;
    }
    public static String getCorrectDocumentFilePath(String filePath) {
//        String correctFilePath = System.getProperty(USER_DIR) + File.separator +  DATA_FOLDER + File.separator + filePath;
        String correctFilePath = System.getProperty(USER_DIR) +
                File.separator + SRC_FOLDER +
                File.separator + TEST_FOLDER +
                File.separator + RESOURCES_FOLDER+
                File.separator + DATA_FOLDER+
//                File.separator + DATA_DOCUMENT_FOLDER+
                File.separator +filePath;
        return correctFilePath;
    }

    public static String getDriverPath(String filePath) {
        String correctFilePath = System.getProperty(USER_DIR) + File.separator + DRIVER_FOLDER + File.separator + filePath;
        return correctFilePath;
    }

    public static String getDownloadFile(String fileName){
        String pathFieDownload=System.getProperty(USER_HOME)+File.separator+DOWNLOAD_FOLDER+ File.separator + fileName;
        return pathFieDownload;
    }

    public static void deleteDirectoryAndFiles(File file) {
        try {
            if (file.isDirectory()) {
                if (file.list().length == 0) {
                    file.delete();
                    LogUtils.info(MessageFormat.format("Deleted directory ''{0}'' successfully",
                            file.getAbsoluteFile()));
                } else {
                    String[] files = file.list();
                    for (String tempFile : files) {
                        File deletedFile = new File(file, tempFile);
                        deleteDirectoryAndFiles(deletedFile);
                    }
                    LogUtils.info(MessageFormat.format("All file are deleted in director ''{0}''",
                            file.getAbsoluteFile()));
                    if (file.list().length == 0) {
                        file.delete();
                        LogUtils.info(MessageFormat.format("Deleted directory ''{0}'' successfully",
                                file.getAbsoluteFile()));
                    }
                }
            } else {
                file.delete();
                LogUtils.info(
                        MessageFormat.format("Deleted file ''{0}'' successfully", file.getAbsoluteFile()));
            }
        } catch (Exception e) {
            LogUtils.error(MessageFormat.format("Cannot delete file/folder ''{0}''. Root cause: {1}",
                    file.getAbsoluteFile(), e.getMessage()));
        }
    }
}
