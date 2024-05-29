/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.jboss.as.quickstarts.kitchensink.model.Member;

import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class MemberRegistration {

    @Inject
    private Logger log;

    @Inject
    Validator validator;

    @Inject
    private Event<Member> memberEventSrc;

    @Transactional
    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        if(!violations.isEmpty()) {
            String validationError;
            if(violations.size() == 1) {
                validationError = violations.iterator().next().getMessage();
            } else {
                validationError = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining("; "));
            }
            throw new Exception(validationError);
        }
        member.persist();
        memberEventSrc.fire(member);
    }
}
