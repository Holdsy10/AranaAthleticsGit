package com.example.curtisholdsworth1.aranaathletics1;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Module for the Sign In Activity
 */
public class SignInTest {

    @Rule
    public ActivityTestRule<SignIn> mActivityTestRule = new ActivityTestRule<SignIn>(SignIn.class);

    private SignIn mSignIn = null;

    @Before
    public void setUp() throws Exception {
        mSignIn = mActivityTestRule.getActivity();
    }


    @Test
    public void testLaunch() {
        View view = mSignIn.findViewById(R.id.autoParentName);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mSignIn = null;
    }
}