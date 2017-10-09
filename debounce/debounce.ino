const int buttonPin = 2;
unsigned long lastTime = 0;
unsigned long debounceDelay = 150;
int lastButtonState = HIGH;
int buttonPresses = 0;
void buttonPressed(){
    long currentTime = millis();
    if((currentTime - lastTime) > debounceDelay){
      buttonPresses++;
      lastTime = currentTime;
    }
}

void setup(){
  Serial.begin(9600);
  pinMode(buttonPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, FALLING);
  
}

void loop(){
  for(int i = 0; i < 200; i++){
    Serial.println(buttonPresses);
    delay(1000);
  }
}

