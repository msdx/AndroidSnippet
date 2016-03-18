package com.githang.android.snippet.app;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v4.app.FragmentActivity;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * FIXME
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @version 2016-03-21
 * @since 2016-03-21
 */
public class AppManagerTest extends TestCase {
    public void testContainSteps() {
        FragmentActivity step1 = new FragmentActivity();
        ListActivity step2 = new ListActivity();
        FragmentActivity step3 = new FragmentActivity();
        AppManager.add(step1);
        AppManager.add(step2);
        AppManager.add(step3);
        Assert.assertTrue("assert contains FragmentActivity", AppManager.contains(FragmentActivity.class));
        Assert.assertFalse("assert not contains Activity", AppManager.contains(Activity.class));
        Assert.assertTrue("assert containSteps FragmentActivity", AppManager.containSteps(FragmentActivity.class));
        Assert.assertFalse("assert not containSteps Activity", AppManager.containSteps(Activity.class));
        Assert.assertFalse("assert not containSteps", AppManager.containSteps(FragmentActivity.class, Activity.class));
        Assert.assertTrue("assert containSteps step1 step2", AppManager.containSteps(FragmentActivity.class, ListActivity.class));
        Assert.assertTrue("assert containSteps step2 step3", AppManager.containSteps(ListActivity.class, FragmentActivity.class));
        Assert.assertFalse("assert containSteps step1 step3", AppManager.containSteps(FragmentActivity.class, FragmentActivity.class));
    }
}
