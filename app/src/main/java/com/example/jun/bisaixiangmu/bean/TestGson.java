package com.example.jun.bisaixiangmu.bean;

public class TestGson {

    /**
     * serverInfo : {"result":"ok"}
     */

    private ServerInfoBean serverInfo;

    public ServerInfoBean getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfoBean serverInfo) {
        this.serverInfo = serverInfo;
    }

    public static class ServerInfoBean {
        /**
         * result : ok
         */

        private String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
