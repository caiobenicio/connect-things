#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <ArduinoJson.h>

static const uint8_t digitalPins[] = {D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13};
static const uint8_t analogicPins[] = {A0};

static const char* ssid = "Elisangela ";
static const char* password = "*";
static const char* mqtt_server = "broker.mqtt-dashboard.com";

static const char* MQTT_SUBSCRIBE = "clientweb/inTopic";      //inTopic MQTT de escuta
static const char* MQTT_PUBLISH = "clientweb/outTopic";       //outTopic MQTT de escrita

unsigned long lastMsg = 0;
char sensorOut[128];

WiFiClient espClient;
PubSubClient client(espClient);
// PubSubClient client(server, 1883, callback, espClient);

StaticJsonDocument<128> sensorJson;

void setup_wifi() {
  delay(10);
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  randomSeed(micros());

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

}

void callback(char* topic, byte* payload, unsigned int length) {
  StaticJsonDocument<256> doc;

  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  Serial.println();

  DeserializationError error = deserializeJson(doc, payload, length);

  if (error) {
    Serial.print(F("deserializeJson() failed: "));
    Serial.println(error.f_str());
    return;
  }

  long user = doc["user"];
  const char* msgType = doc["msgType"];

  switch (msgType[0]) {
    case 'P':
      listPortResp(user);
      Serial.print(" msgType = P ");
      break;
    case 'A':
      int pin = doc["pin"];
      int action = doc["action"];
      writeAction(pin, action);
      Serial.print(" msgType = A ");
      break;
  }

}

void listPortResp(long user) {
  DynamicJsonDocument json(300);
  json["user"] = user;
  json["msgType"] = "P";

  JsonArray pinAnalogic = json.createNestedArray("pinAnalogic");
  pinIn.add("A0");

  JsonArray pinDigital = json.createNestedArray("pinDigital");
  pinOut.add("D0");
  pinOut.add("D1");
  pinOut.add("D2");
  pinOut.add("D3");
  pinOut.add("D4");
  pinOut.add("D5");
  pinOut.add("D6");
  pinOut.add("D7");
  pinOut.add("D8");
  pinOut.add("D9");
  pinOut.add("D10");
  pinOut.add("D11");
  pinOut.add("D12");
  pinOut.add("D13");

  char out[300];
  serializeJson(json, out);
  client.publish(MQTT_PUBLISH, out);
}

void writeAction(int pos, int action) {
  digitalWrite(digitalPins[pos], action);
}

void reconnect() {
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    String clientId = "ESP8266Client-";
    clientId += String(random(0xffff), HEX);

    if (client.connect(clientId.c_str())) {
      Serial.println("connected");

      client.subscribe(MQTT_SUBSCRIBE);
      Serial.print(" Topico Subscribe: ");
      Serial.println(MQTT_SUBSCRIBE);
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      delay(5000);
    }
  }
}

void setup() {
  delay(200);
  Serial.begin(115200);

  for (int i = 0; i <= 14; i++) {
    if(i >= 2 && i <= 7) {
      pinMode(digitalPins[i], OUTPUT);
      digitalWrite(digitalPins[i], LOW);
      delay(10);
    } else if(i >= 8) {
      pinMode(digitalPins[i], OUTPUT);
      digitalWrite(digitalPins[i], LOW);
      delay(10);
    }
  }

  pinMode(analogicPins[0], OUTPUT);

  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void loop() {
  if (!client.connected()) {
    reconnect();
  }

  client.loop();

  unsigned long now = millis();
  if (now - lastMsg > 3000) {
    lastMsg = now;

    int sensorValue = digitalRead(pinoPir); //Le o valor do sensor PIR

    if (sensorValue == HIGH) {
      publishSensorData("D3");
      Serial.println("movimento detectado");
    }
  }
}

void publishSensorData(String pinoPir) {
  sensorJson["msgType"] = "S";
  sensorJson["pin"] = pinoPir;
  sensorJson["status"] = HIGH;

  serializeJson(sensorJson, sensorOut);
  client.publish(MQTT_PUBLISH, sensorOut);
}
