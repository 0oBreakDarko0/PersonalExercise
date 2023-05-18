package org.myself.study.internet.im.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import org.myself.study.internet.im.attribute.Attributes;

public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);

        return attr.get() != null;
    }
}
