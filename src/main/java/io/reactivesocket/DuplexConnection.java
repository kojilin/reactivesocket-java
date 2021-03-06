/**
 * Copyright 2015 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.reactivesocket;

import java.io.Closeable;

import org.reactivestreams.Publisher;

import io.reactivesocket.rx.Completable;
import io.reactivesocket.rx.Observable;

/**
 * Represents a connection with input/output that the protocol uses. 
 */
public interface DuplexConnection extends Closeable {
    // TODO should we call this 'Connection'? 'SocketConnection'? 'ReactiveSocketConnection'?

	Observable<Frame> getInput();

	void addOutput(Publisher<Frame> o, Completable callback);

	default void addOutput(Frame frame, Completable callback) {
        addOutput(s -> {
            s.onNext(frame);
            s.onComplete();
        }, callback);
	}
}
