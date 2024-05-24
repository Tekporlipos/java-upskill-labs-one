package com.amalitech.upskilling.week_one.lab_two.structural.proxy;

interface FileDownloader {
    void downloadFile(String fileName);
}

class RealFileDownloader implements FileDownloader {
    @Override
    public void downloadFile(String fileName) {
        System.out.println("Downloading file: " + fileName);
    }
}

class FileDownloadProxy implements FileDownloader {
    private final RealFileDownloader realDownloader;
    private final boolean hasAuthorization;

    public FileDownloadProxy(boolean hasAuthorization) {
        this.realDownloader = new RealFileDownloader();
        this.hasAuthorization = hasAuthorization;
    }

    @Override
    public void downloadFile(String fileName) {
        if (hasAuthorization) {
            System.out.println("Authorization granted. Starting download...");
            realDownloader.downloadFile(fileName);
        } else {
            System.out.println("Authorization required to download file.");
        }
    }
}

class ProxyPatternFileDownloadExample {
    public static void main(String[] args) {
        FileDownloader proxy = new FileDownloadProxy(true);
        proxy.downloadFile("example.pdf");

        FileDownloader proxyNoAuth = new FileDownloadProxy(false);
        proxyNoAuth.downloadFile("example.pdf");
    }
}
