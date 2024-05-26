package org.jboss.as.quickstarts.kitchensink.test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.jboss.as.quickstarts.kitchensink.controller.MemberController;
import org.jboss.as.quickstarts.kitchensink.data.MemberListProducer;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class MemberControllerIT {

    @Inject
    private MemberController memberController;

    @Inject
    MemberListProducer memberListProducer;

    @Test
    public void testSuccessfulRegister() throws Exception {
        FacesContext context = FacesContextMocker.mockFacesContext();
        try {
            Member member = memberController.getNewMember();
            member.setName("Adam");
            member.setEmail("adam@mail.com");
            member.setPhoneNumber("1234567890");
            memberController.register();
            Assertions.assertEquals(context.getMessageList().getFirst().getDetail(), "Registration successful");
            Assertions.assertTrue(memberListProducer.getMembers().stream().anyMatch(m -> "adam@mail.com".equals(m.getEmail())));
        } finally {
            context.release();
        }
    }

    @Test
    public void testFailedRegister() throws Exception {
        FacesContext context = FacesContextMocker.mockFacesContext();
        try {
            Member member = memberController.getNewMember();
            member.setName("Adam123");
            member.setEmail("adam123@mail.com");
            member.setPhoneNumber("1234567890");
            memberController.register();
            Assertions.assertEquals(context.getMessageList().getFirst().getDetail(), "Registration unsuccessful");
            Assertions.assertFalse(memberListProducer.getMembers().stream().anyMatch(m -> "adam123@mail.com".equals(m.getEmail())));
        } finally {
            context.release();
        }
    }

}
