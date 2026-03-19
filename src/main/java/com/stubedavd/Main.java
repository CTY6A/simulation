package com.stubedavd;

import com.stubedavd.core.Simulation;
import com.stubedavd.utils.ControlPauseThread;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();

        ControlPauseThread controlPauseThread = new ControlPauseThread(simulation);
        controlPauseThread.start();

        simulation.startSimulation();
    }
}