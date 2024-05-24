package com.amalitech.upskilling.week_one.lab_two.structural.proxy;

interface RemoteService {
    String fetchData();
}

class RealRemoteService implements RemoteService {
    @Override
    public String fetchData() {
        return "Data from remote service";
    }
}

class RemoteServiceProxy implements RemoteService {
    private final RealRemoteService realService;
    private String cachedData;

    public RemoteServiceProxy() {
        this.realService = new RealRemoteService();
    }

    @Override
    public String fetchData() {
        if (cachedData == null) {
            cachedData = realService.fetchData();
        }
        return cachedData;
    }
}

class ProxyPatternRemoteServiceExample {
    public static void main(String[] args) {
        RemoteService service = new RemoteServiceProxy();
        System.out.println(service.fetchData());
        System.out.println(service.fetchData());
    }
}

