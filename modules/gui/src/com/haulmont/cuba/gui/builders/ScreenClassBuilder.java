/*
 * Copyright (c) 2008-2018 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.gui.builders;

import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenOptions;

public class ScreenClassBuilder<S extends Screen> extends ScreenBuilder {
    protected Class<S> screenClass;

    public ScreenClassBuilder(ScreenBuilder builder, Class<S> screenClass) {
        super(builder);

        this.screenClass = screenClass;
    }

    @Override
    public ScreenClassBuilder<S> withLaunchMode(Screens.LaunchMode launchMode) {
        super.withLaunchMode(launchMode);
        return this;
    }

    @Override
    public ScreenBuilder withOptions(ScreenOptions options) {
        super.withOptions(options);
        return this;
    }

    @Override
    public ScreenClassBuilder<S> withScreen(String screenId) {
        throw new IllegalStateException("ScreenClassBuilder does not support screenId");
    }

    public Class<S> getScreenClass() {
        return screenClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public S build() {
        return (S) super.build();
    }
}