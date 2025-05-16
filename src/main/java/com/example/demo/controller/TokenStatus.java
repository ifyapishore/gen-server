package com.example.demo.controller;

public enum TokenStatus {
    /**
     * Token is ok
     */
    ok,
    /**
     * Token is ok, but must be renewed
     */
    obsolete,
    /**
     * Token is built by previous version of the system.
     * Client software must be updated (like page refresh or reinstall desktop software).
     */
    deprecated
}
