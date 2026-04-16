""" Core Python: The "FareCalc" Travel Optimizer
Business Case: A ride-sharing startup, "CityCab," needs a backend script to calculate fares. The fare isn't flat; it changes based on the time of day (Peak Hours) and the type of vehicle requested.
Problem Statement
Write a script that calculates the final "Ride Estimate" based on distance, vehicle type, and a "Surge Pricing" multiplier.
Student Tasks:
1.	Dictionary Mapping: Store rates in a dictionary: {'Economy': 10, 'Premium': 18, 'SUV': 25} (rates per km).
2.	Surge Logic: Ask the user for the "Hour of Day" (0-23). If the hour is between 17 and 20 (5 PM - 8 PM), apply a 1.5x Surge Multiplier to the total.
3.	Function Definition: Create a function calculate_fare(km, type, hour) that returns the final price.
4.	Error Handling: If the user enters a vehicle type not in your dictionary, use a try-except block or an if-in check to provide a "Service Not Available" message.
"""
def calculate_fare(km, vehicle_type, hour):
    rates = {
        "Economy": 10,
        "Premium": 18,
        "SUV": 25
    }
    if vehicle_type not in rates:
        return None
    base_fare = km * rates[vehicle_type]
    if 17 <= hour <= 20:
        surge = 1.5
    else:
        surge = 1.0
    return base_fare * surge
def main():
    try:
        km = float(input("Enter distance (in km): "))
        vehicle_type = input("Enter vehicle type (Economy/Premium/SUV): ")
        hour = int(input("Enter hour of travel (0-23): "))
        if km <= 0:
            print("Distance must be greater than 0.")
            return
        if hour < 0 or hour > 23:
            print("Hour must be between 0 and 23.")
            return
        fare = calculate_fare(km, vehicle_type, hour)
        if fare is None:
            print("Service not available for selected vehicle type.")
        else:
            print("\n------ Price Receipt ------")
            print(f"Distance: {km} km")
            print(f"Vehicle Type: {vehicle_type}")
            print(f"Travel Hour: {hour}")
            print(f"Total Fare: ₹{fare:.2f}")
            print("----------------------------")
    except ValueError:
        print("Invalid input. Please enter correct values.")
if __name__ == "__main__":
    main()