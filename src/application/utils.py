import geoip2.database

def get_ip_location(ip_address):
    # Path to the GeoLite2 database file (in CSV format)
    database_path = 'path/to/GeoLite2-City-CSV/GeoLite2-City-Locations-en.csv'

    # Create a reader for the GeoLite2 database
    reader = geoip2.database.Reader(database_path)

    try:
        # Retrieve the geolocation information for the IP address
        response = reader.city(ip_address)
        
        # Extract relevant information (e.g., country, city, latitude, longitude)
        country = response.country.name
        city = response.city.name
        latitude = response.location.latitude
        longitude = response.location.longitude

        # Print the location information
        print(f"IP Address: {ip_address}")
        print(f"Country: {country}")
        print(f"City: {city}")
        print(f"Latitude: {latitude}")
        print(f"Longitude: {longitude}")

    except geoip2.errors.AddressNotFoundError:
        print("IP address not found in the database.")

    finally:
        # Close the database reader
        reader.close()

# Example usage
get_ip_location('8.8.8.8') 