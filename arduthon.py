import serial

max_temp=0
min_temp=99
arduino = serial.Serial('COM4', 9600, timeout=0) #check for port of your pc and baud rate of arduino
print("\n####		HAQT		####\n")
while True:
	data = arduino.readline() 
	if data:
		humidity = int(data[0])
		temperature = int(data[1])
		airq = int(data[2])
		max_temp = temperature if temperature > max_temp else max_temp
		min_temp = temperature if temperature < min_temp else min_temp

		print(f"\t*** Max Temp: {max_temp}°c Min Temp: {min_temp}°c ***",end='\r')
		
		print(f"Humidity: {humidity}%\tTemperature: {temperature}°c\tAir Quality: {airq} PPM",end='')