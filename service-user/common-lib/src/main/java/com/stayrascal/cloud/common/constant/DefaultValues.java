package com.stayrascal.cloud.common.constant;

public class DefaultValues {
    private DefaultValues() {
    }

    public static final class Auth {
        public Auth() {
        }

        public static int tokenExpiredMinute() {
            return 60;
        }

        public static String tokenSecret() {
            return "secret";
        }
    }

    public static final class System {
        public System() {
        }

        public static String defaultEncoding() {
            return "UTF-8";
        }
    }
}
