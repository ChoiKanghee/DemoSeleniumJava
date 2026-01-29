package org.example.utils;

public class ScenarioResult {

    private final String feature;
    private final String name;
    private final String status;     // "PASSED" hoặc "FAILED"
    private final long   durationMs; // thời gian chạy (ms)

    public ScenarioResult(String feature, String name,
                          String status, long durationMs) {
        this.feature    = feature;
        this.name       = name;
        this.status     = status;
        this.durationMs = durationMs;
    }

    /* ----- Getter ----- */
    public String getFeature()   { return feature; }
    public String getName()      { return name; }
    public String getStatus()    { return status; }
    public long   getDurationMs(){ return durationMs; }
}