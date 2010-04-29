/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.sip.media;

import android.util.Log;

import java.net.DatagramSocket;

public class RtpFactory {
    public static RtpSession[] getSystemSupportedAudioSessions() {
        return new RtpSession[] {
                new RtpAudioSession(0), new RtpAudioSession(8)};
    }

    // returns null if codecId is not supported by the system
    public static RtpSession createAudioSession(int codecId) {
        return create(codecId);
    }

    public static RtpSession createAudioSession(
            int codecId, int remoteSampleRate, DatagramSocket socket) {
        RtpAudioSession s = create(codecId);
        if (s == null) return null;
        s.set(remoteSampleRate, socket);
        return s;
    }

    private static RtpAudioSession create(int codecId) {
        for (RtpSession s : getSystemSupportedAudioSessions()) {
            if (s.getCodecId() == codecId) {
                return new RtpAudioSession(codecId);
            }
        }
        return null;
    }

    static Encoder createEncoder(int id) {
        switch (id) {
        case 8:
            return new G711ACodec();
        default:
            return new G711UCodec();
        }
    }

    static Decoder createDecoder(int id) {
        switch (id) {
        case 8:
            return new G711ACodec();
        default:
            return new G711UCodec();
        }
    }
}
