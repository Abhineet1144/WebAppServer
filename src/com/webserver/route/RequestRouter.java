package com.webserver.route;

public abstract class RequestRouter {
    public abstract HandlerOrder getRouteOrder(String endpoint);

    public static boolean matches(String pattern, String path) {
        if (pattern == null || path == null) {
            return false;
        }

        if ("/".equals(pattern)) {
            return true;
        }

        if (pattern.startsWith("*.")) {
            String extension = pattern.substring(1); // ".jsp"
            return path.endsWith(extension);
        }

        if (pattern.endsWith("/*")) {
            String prefix = pattern.substring(0, pattern.length() - 2);

            return path.equals(prefix)
                    || path.startsWith(prefix + "/");
        }

        // Exact match
        return path.equals(pattern);
    }
}
