public class Snippet__5642265 {

    @Override
        public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
            List<Cookie> cookies = super.parse(header, origin);
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(StickyCookieHolder.COOKIE_NAME)) {
                    // store it in the TestStickySessionRule threadlocal var
                    StickyCookieHolder.setTestStickySessionCookie(cookie);
                }
            }
            return cookies;
        }

}
