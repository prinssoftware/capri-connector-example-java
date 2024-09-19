# Capri Connector Java Example

This project is a Java Maven example of a simple client implementation for Capri web services (SOAP).

## Installation

1. **Obtain Access:**
   First, contact Prins Software ([https://prins.cz](https://prins.cz)) to request access to their web services. If you already have access, you can skip this step.

2. **Install Prerequisites:**
    - Install JDK 21.
    - Install Maven 3.x.

3. **Prepare the Certificate:**
    - Copy your `.p12` certificate file into this folder and rename it to `cc-key.p12`.

4. **Download the WSDL File:**
    - Run the following `curl` command to download the WSDL file:
      ```bash
      curl -L --cert ./cc-key.p12:PASSWORD --cert-type P12 -o capri-connector.wsdl https://secure.prins.cz:8443/CapriConnector?wsdl
      ```

5. **Update Credentials:**
    - Open [Main.java](./src/main/java/cz/prins/capriconnector/client/example/Main.java) and fill in your credentials where indicated by `//TODO`.

6. **Build the Project:**
    - Run the following command to build the project:
      ```bash
      mvn install
      ```

7. **Run the Example:**
    - Execute the following command to run the example:
      ```bash
      mvn exec:java -Dexec.mainClass=cz.prins.capriconnector.client.example.Main
      ```
