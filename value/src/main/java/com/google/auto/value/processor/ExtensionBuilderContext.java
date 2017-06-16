/*
 * Copyright (C) 2015 Google Inc.
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
package com.google.auto.value.processor;

import com.google.auto.value.extension.AutoValueExtension;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

public class ExtensionBuilderContext implements AutoValueExtension.BuilderContext {

  private final TypeElement builderClass;
  private final ExecutableElement buildMethod;
  private final ImmutableMultimap<String, ExecutableElement> setters;
  private final ImmutableMap<String, ExecutableElement> propertyBuilders;

  ExtensionBuilderContext(
      TypeElement builderClass,
      ExecutableElement buildMethod,
      Multimap<String, ExecutableElement> setters,
      Map<String, ExecutableElement> propertyBuilders) {
    this.builderClass = builderClass;
    this.buildMethod = buildMethod;
    this.setters = ImmutableMultimap.copyOf(setters);
    this.propertyBuilders = ImmutableMap.copyOf(propertyBuilders);
  }

  @Override
  public TypeElement builderClass() {
    return builderClass;
  }

  @Override
  public ExecutableElement buildMethod() {
    return buildMethod;
  }

  @Override
  public Map<String, Set<ExecutableElement>> setters() {
    return Maps.transformValues(setters.asMap(), Sets::newHashSet);
  }

  @Override
  public Map<String, ExecutableElement> propertyBuilders() {
    return propertyBuilders;
  }
}
