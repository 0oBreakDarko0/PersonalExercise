package org.myself.study.internet.netty.chapter17.attribute;

import io.netty.util.AttributeKey;
import org.myself.study.internet.netty.chapter17.session.Session;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
