/* 
 * Arduino Nano 33 BLE Sense rev2 measures the following:
 * - temperature (±0.1°C)/ HS3003 sensor
 * - humidity (%RH)/ HS3003 sensor
 * - barometric pressure (260-1260 kPa) / LPS22HB sensor
 (* - proximity (0-255)/ APDS9960 sensor ---> does't work if meassuring light)
 * - light intensity (0-4097) / APDS9960 sensor
 * - RGB levels (0-4097) / APDS9960 sensor
 * - tilt/position (x, y axes) / BMI270_BMM150 sensors
 * - time elapsed (milliseconds)
 *
 * The data with the device ID are serialized and sent via serial as CSV (send as Json object option also provided).
 *
 * A timestamp (in ISO 8601 fomrat) can also be sent via the TimeLib.h library. The time is synced to the Current Epoch Unix Timestamp
 * by sending the ltter T followed by ten digit time (as seconds since Jan 1 1970).
 *
 */

// #include <ArduinoJson.h>
// #include <TimeLib.h>
#include <Arduino_HS300x.h>
#include <Arduino_LPS22HB.h>
#include "Arduino_BMI270_BMM150.h"
#include <Arduino_APDS9960.h>
#include <string.h>
#include <PDM.h>

#define TIME_HEADER "T"  // Header tag for serial time sync message
#define TIME_REQUEST 7   // ASCII bell character requests a time sync message

float temperature;
float humidity;
float pressure;
// int proximity;
int r, g, b, light;  // red, green, blue, light intensity
int degreesX = 0;
int degreesY = 0;
long timelapsed;
// char timestamp[25];  // big enough for your longest string, including a null terminator
String deviceID = "arduino-nano";
// StaticJsonDocument<256> root;

void setup() {
  Serial.begin(9600);
  while (!Serial);
  pinMode(13, OUTPUT);
  // setSyncProvider(requestSync);  //set function to call when sync required
  // Serial.println("To sync device send 'T + Current Epoch Unix Timestamp'");
  if (!HS300x.begin()) {
    Serial.println("Failed to initialize humidity temperature sensor!");
    while (1)
      ;
  }
  if (!BARO.begin()) {
    Serial.println("Failed to initialize pressure sensor!");
    while (1)
      ;
  }
  if (!IMU.begin()) {
    Serial.println("Failed to initialize IMU!");
    while (1)
      ;
  }
  if (!APDS.begin()) {
    Serial.println("Error initializing APDS9960 sensor.");
  }
  // Serial.print("Accelerometer sample rate = ");
  // Serial.print(IMU.accelerationSampleRate());
  // Serial.println(" Hz");
}

void loop() {
  timelapsed = millis();
  // read temperature, humidity, pressure
  temperature = HS300x.readTemperature();
  humidity = HS300x.readHumidity();
  // temperature = (int)(HS300x.readTemperature() * 100 + 0.5) / 100.0;  // for json: rounding the float to a two decimal int
  // humidity = (in)(HS300x.readHumidity() * 100 + 0.5) / 100.0;        // for json: rounding the float to a two decimal int
  pressure = BARO.readPressure();

  // read the gyroscope
  float x, y, z;
  if (IMU.accelerationAvailable()) {
    IMU.readAcceleration(x, y, z);
    if (x > 0.1) {
      x = 100 * x;
      degreesX = map(x, 0, 97, 0, 90);
    }
    if (x < -0.1) {
      x = 100 * x;
      degreesX = (-1) * (map(x, 0, -100, 0, 90));
    }
    if (y > 0.1) {
      y = 100 * y;
      degreesY = map(y, 0, 97, 0, 90);
    }
    if (y < -0.1) {
      y = 100 * y;
      degreesY = (-1) * (map(y, 0, -100, 0, 90));
    }
  }

  // check if a color reading is available
  while (!APDS.colorAvailable()) {
    delay(5);
  }
  // read the color
  APDS.readColor(r, g, b, light);
  if (r > g & r > b) {
    digitalWrite(LEDR, LOW);
    digitalWrite(LEDG, HIGH);
    digitalWrite(LEDB, HIGH);
  } else if (g > r & g > b) {
    digitalWrite(LEDG, LOW);
    digitalWrite(LEDR, HIGH);
    digitalWrite(LEDB, HIGH);
  } else if (b > g & b > r) {
    digitalWrite(LEDB, LOW);
    digitalWrite(LEDR, HIGH);
    digitalWrite(LEDG, HIGH);
  } else {
    digitalWrite(LEDR, HIGH);
    digitalWrite(LEDG, HIGH);
    digitalWrite(LEDB, HIGH);
  }
  // read the proximity
  // proximity = APDS.readProximity();

  // get the timestamp
  // if (Serial.available()) {
  //   processSyncMessage();
  // }
  // if (timeStatus() != timeNotSet) {
  //   // timestamp as ISO 8601 string date format
  //   snprintf(timestamp, sizeof(timestamp), "%4d-%02d-%02dT%2d:%2d:%02d+0200", year(), month(), day(), hour(), minute(), second());
  // }

  // Serial.print(deviceID);
  // Serial.print(",");
  Serial.print(temperature);
  Serial.print(",");
  Serial.print(humidity);
  Serial.print(",");
  Serial.print(pressure);
  Serial.print(",");
  // Serial.print(proximity);
  // Serial.print(",");
  Serial.print(light);
  Serial.print(",");
  Serial.print(r);
  Serial.print(",");
  Serial.print(g);
  Serial.print(",");
  Serial.print(b);
  Serial.print(",");
  Serial.print(degreesX);
  Serial.print(",");
  Serial.print(degreesY);
  // Serial.print(",");
  // Serial.print(timelapsed);
  // Serial.print(",");
  // Serial.print(timestamp);
  Serial.println();

  // serialize stream to json
  // JsonObject root = doc.to<JsonObject>();
  // root["temperature"] = temperature;
  // root["humidity"] = humidity;
  // root["pressure"] = pressure;
  // root["proximity"] = proximity;
  // root["light"] = light;
  // JsonArray colors = doc.createNestedArray("colors");
  // colors.add(r);
  // colors.add(g);
  // colors.add(b);
  // JsonArray tilt = doc.createNestedArray("tilt");
  // tilt.add(degreesX);
  // tilt.add(degreesY);
  // root["time"] = timeE;
  // root["deviceID"] = deviceID;
  // root["timestamp"] = timestampString;
  // serializeJson(root, Serial);
  // serializeJsonPretty(root, Serial);
  // Serial.println();
  
  delay(1000);
}

// set the time utility function
// void processSyncMessage() {
//   unsigned long pctime;
//   const unsigned long DEFAULT_TIME = 1357041600;  // Jan 1 2013
//   if (Serial.find(TIME_HEADER)) {
//     pctime = Serial.parseInt();
//     if (pctime >= DEFAULT_TIME) {  // check the integer is a valid time (greater than Jan 1 2013)
//       setTime(pctime);             // Sync Arduino clock to the time received on the serial port
//     }
//   }
// }

// time_t requestSync() {
//   Serial.write(TIME_REQUEST);
//   return 0;  // the time will be sent later in response to serial mesg
// }
