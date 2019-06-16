package example.programming;

import ev3dev.actuators.Sound;
import ev3dev.actuators.lego.motors.BaseRegulatedMotor;
import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import ev3dev.sensors.ev3.EV3ColorSensor;
import ev3dev.sensors.ev3.EV3UltrasonicSensor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Maze {

	public static void main (String[] args) {
		
		System.out.println("Creating Motor A and D");
		final EV3LargeRegulatedMotor motorLeft= new EV3LargeRegulatedMotor (MotorPort.A);
		final EV3LargeRegulatedMotor motorRight= new EV3LargeRegulatedMotor (MotorPort.D);
		EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S1);
		EV3ColorSensor color1 = new EV3ColorSensor(SensorPort.S2);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Emergency Stop");
                motorLeft.stop();
                motorRight.stop();
            }
        }));
        
		motorLeft.setSpeed(500);
		motorRight.setSpeed(500);
		System.out.println("Go Forward");
		motorLeft.forward();
		motorRight.forward();
		@SuppressWarnings("unused")
		final SensorMode stpity=((EV3ColorSensor) color1).getColorIDMode();
		while(true) {
			
			SampleProvider sp1 = color1.getColorIDMode();
			int sampleSize = sp1.sampleSize();
			@SuppressWarnings("unused")
			float[] sample = new float[sampleSize];
			if(sampleSize == Color.YELLOW)	{
				Sound.getInstance().beep();
				System.out.println("Turn Right");
				motorLeft.stop();
				motorRight.stop();
				Delay.msDelay(500);				
				motorLeft.setSpeed(0);
				motorRight.setSpeed(500);
				motorLeft.forward();
				Delay.msDelay(1000);	
				break;
			}
		}
			}
		}
