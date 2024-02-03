# Setup Pixalink Explore

Introduction and steps to set up Pixalink environment locally.

## Getting Started

> [!IMPORTANT]
>Requirement
>-   [Xampp](https://www.apachefriends.org/) (Windows)
>-   [MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html) (if you dont use xampp)
>-   [Valet](https://laravel.com/docs/9.x/valet) (Mac OS)
>-   [Composer](https://getcomposer.org/doc/00-intro.md)
>-   [PHP8](https://www.php.net/downloads.php#v8.0.11)
>-   [NPM](https://www.npmjs.com/get-npm)

## Setting up

### Clone from Github & Open project

```shell
git clone <repository url>
```

Open the project in phpstorm or other platform.

### Copy and Setting up the environment file

Update your environment variable based on your needs.

-   Copy and paste `.env.ci` file and change file name to `.env`.
-   Change `DB_HOST=127.0.0.1`,
-   Change `DB_DATABASE=database_name example pixalink_explore`.
-   Remove _~~password~~_ `DB_PASSWORD=`.

## Installing Dependencies

Assume you have installed Composer and Node and NPM <br/>
Installing Composer and Npm dependencies

### Composer Credential & Dependencies

1. Get **Credential Key** from **CCK** and the below items for **.env** file

<ul>
    <li>ADASMS_TOKEN</li>
    <li>MATTERPORT_USERNAME</li>
    <li>MATTERPORT_PASSWORD</li>
    <li>GREEN_API_INSTANCE_ID</li>
    <li>GREEN_API_INSTANCE_TOKEN</li>
</ul>
2. Run the **composer config**

```shell
 composer config http-basic.spark.laravel.com pixalinkdev@gmail.com <Key>
```
>[!NOTE]
>replace the <key> with the **Credential Key** from **CCK*

3. Run the **composer install**

```shell
 composer install
```

### Stripe account

1. Create a stripe account from https://dashboard.stripe.com/register
2. Fill up everything and create an account
3. For the bank account, you can skip it.
4. You will see it force you to fill up the bank account, just click **X** on top left corner.

### Stripe API Key

1. Go to https://dashboard.stripe.com/test/apikeys

> [!NOTE]
> Copy the **Publishable key** and **Secret key** and paste it to **.env** file

```shell
STRIPE_KEY=
STRIPE_SECRET=
```

3. Go to https://dashboard.stripe.com/test/products
4. Click **Add product** and fill up the product name and price

<ul>
    1. Product :Pixalink Explore
    <li>Product name: Pixalink Explore</li>
    <li>Price: 365</li>
    <li>Recurring</li>
    <li>Yearly</li>
    2. Product :Email Channel - Pay as you go
    <li>Product name: Email Channel - Pay as you go</li>
    <li>Price: 0.0008</li>
    <li>Recurring</li>
    <li>Monthly</li>
    3. Product: Email Attachment - Pay as you go
    <li>Product name: Email Attachment - Pay as you go</li>
    <li>Price: 0.00000000093</li>
    <li>Recurring</li>
    <li>Monthly</li>
    4. Product: SMS Channel - Pay as you go
    <li>Product name: Price for SMS Metered - Pay as you go</li>
    <li>Use <b>Graduated Pricing</b> For the first 1 to 55 FLAT AMOUNT 10.00 For the rest 	
PRICE PER UNIT 0.18</li>
    <li>Recurring</li>
    <li>Monthly</li>
    5. WhatsApp Channel
    <li>Product name: WhatsApp Channel</li>
    <li>3 Prices 1800/year as Default, 1000/6 months, 600/3 months</li>
    <li>Recurring</li>
</ul>
5. Copy all the api key into **.env** file

```shell
STRIPE_PLANS_SMS_METERED_ID=
STRIPE_PLANS_DEFAULT_YEARLY_ID=
STRIPE_PLANS_EMAIL_METERED_ID=
STRIPE_PLANS_EMAIL_ATTACHMENT_METERED_ID=

STRIPE_PLANS_WHATSAPP_GREEN_3_MONTH_ID=
STRIPE_PLANS_WHATSAPP_GREEN_6_MONTH_ID=
STRIPE_PLANS_WHATSAPP_GREEN_YEARLY_ID=
```
> [!NOTE]
> **[STRIPE_PLANS_DEFAULT_YEARLY_ID]** represents the Pixalink Explore product.

### Setup FFMPEG

1. The easiest way to install is using chocolatey (windows)
2. https://docs.chocolatey.org/en-us/choco/setup#install-with-powershell.exe Go to this website and install the
   chocolatey with **PowerShell** first
3. After install the chocolatey, open the **PowerShell** and run the command below

```shell
 choco install ffmpeg
```

4. It will ask: do you want to run the script, just type **Y** and enter
5. If you are using windows, you want to set FFMPEG_PATH and FFPROBE_PATH
6. You will need to set the path at **.env** file. Below is an example.

```shell
FFMPEG_PATH="C:\\ProgramData\\chocolatey\\bin\\ffmpeg.exe"
FFPROBE_PATH="C:\\ProgramData\\chocolatey\\bin\\ffprobe.exe"
```

### Database Migration & Seed the data

1. Open **XAMPP Control Panel**.
2. Start **Apache & MySQL**.
3. Click on **MySQL Admin**.
4. Create new database ( ex._pixalink_explore_ ).

### Migrate Database and link storage.

```shell
 php artisan migrate --seed
 php artisan storage:link
```

> [!NOTE]
>- If you encounter MySQL-related issues during schema migration using XAMPP, find the `mysql.exe` file in `C:\xampp\mysql\bin`. Add its path to the system environment variables:
>   - Search for "Edit the system environment variables" using the Windows search bar.
>   - Access the environment variables, double-click on `Path`.
>   - Click `New` and paste the `C:\xampp\mysql\bin` path (your path may vary).
>   - Click `OK` to close all windows.

### Installing NPM dependencies and run in dev

```shell
npm install
npm run dev
```

### Setting up Virtual Host

Follow the two file paths below to set up the virtual hosts.

```shell
c:/Windows/System32/drivers/etc/hosts
c:/xampp/apache/conf/extra/vhosts
```
> [!NOTE]
>Vhost is the httpd-vhosts.conf file, which serves as the Virtual Host configuration file.

1. Open the vhosts and add the code below at the bottom

```shell
<VirtualHost *:80>
    DocumentRoot "C:/xampp/htdocs/Pixalink-Explore/public"
    ServerName explore.pixalink.io.test
</VirtualHost>
```

2. Open the hosts and add the code below at the bottom

```shell
127.0.0.1       explore.pixalink.io.test
```
3. If system say unable to save the file, you can download https://www.sublimetext.com/ and run the app with
   administrator and open the file again to edit.
4. Restart the Apache in XAMPP Control Panel.

> [!TIP]
> Alternative Method (if you prefer not to download the application):
> 
> 3. Copy the hosts file to another directory.
> 4. Open the file using Notepad.
> 5. Add the provided code: `127.0.0.1       explore.pixalink.io.test`.
> 6. Save the changes.
> 7. Copy the modified `host` file.
> 8. Remove the original hosts file from `C:/Windows/System32/drivers/etc/`.
> 9. Paste your `host` to `C:/Windows/System32/drivers/etc/`.


### Set up SSL / HTTPS

1. Create a file in C:\xampp\apache
2. name the file https.txt
3. Open the file and paste the code below

```shell
authorityKeyIdentifier=keyid,issuer
basicConstraints=CA:FALSE
keyUsage = digitalSignature, nonRepudiation, keyEncipherment, dataEncipherment
subjectAltName = @alt_names

[alt_names]
DNS.1 = explore.pixalink.io.test
DNS.2 = localhost
```

4. Change the file extension from https.txt to https.ext
5. Edit the file in **C:\xampp\apache\makecert.bat** and replace the line **bin\openssl x509 -in server.csr -out
   server.crt -req -signkey server.key -days**

```shell
bin\openssl x509 -in server.csr -out server.crt -req -signkey server.key -days 500 -sha256 -extfile https.ext
```

6. Open php.ini and find the line **;extension=openssl** and remove the **;** and save it
7. Open CMD and run the command below

```shell
cd C:\xampp\apache
makecert.bat
```

8. It will ask you to fill up the information below

```shell
Enter PEM pass phrase: **password**<br/>
Country Name (2 letter code) [AU]: **MY** <br/>
State or Province Name (full name) [Some-State]: **Selangor** <br/>
Locality Name (eg, city) []: **Petaling Jaya** <br/>
Organization Name (eg, company) [Internet Widgits Pty Ltd]: **Pixalink** <br/>
Organizational Unit Name (eg, section) []: **Pixalink** <br/>
Common Name (e.g. server FQDN or YOUR name) []: **explore.pixalink.io.test** <br/>
Email Address []: **
```

9. After that, you will see the folders ssl.crt and ssl.key in **xampp/apache/conf**, it will have this two file **server.crt** and **server.key**
10. double click the file server.crt and install it to **Trusted Root Certification Authorities** and **Local Computer**
11. Win+R and type **certmgr.msc** and enter
12. Go to **Trusted Root Certification Authorities** and **Certificates** and find the **localhost** and **your virtual
    host site Example: explore.pixalink.io.test** and you have complete install
    
> [!NOTE]
>If the certificate is not found in `certmgr.msc > Trusted Root Certification Authorities > Certificates`:
>1. Double Click on the **server.crt** file located in **xampp/apache/conf/ssl.crt**.
>2. Click on "Install Certificates."
>3. Click "Next," then select "Place all certificates in the following store."
>4. Click "Browse."
>5. Choose [Trusted Root Certificate Authorities], then click "OK."
>6. Win+R and type **certmgr.msc** and press Enter.
>7. Navigate to **Trusted Root Certification Authorities > Certificates.**
>8. Find entries for both **localhost** and your virtual host site (e.g., explore.pixalink.io.test).
>9. Confirm the certificate installation is complete.

After Confirm the certificate installation is complete.

13. Open the folder below

```shell
c:/xampp/apache/conf/extra/vhosts
```

14. Open the vhosts and add the code below at the bottom

```shell
<VirtualHost *:443>
    DocumentRoot "C:/xampp/htdocs/Pixalink-Explore/public"
    ServerName explore.pixalink.io.test

    SSLEngine On
    SSLCertificateFile "C:/xampp/apache/conf/ssl.crt/server.crt"
    SSLCertificateKeyFile "C:/xampp/apache/conf/ssl.key/server.key"

    <Directory "C:/xampp/htdocs/Pixalink-Explore/public">
        Options All
        AllowOverride All
        order allow,deny
        allow from all
    </Directory>
</VirtualHost>
```

15. Restart the Apache in XAMPP Control Panel.
16. Go back to the .env file and change the **APP_URL** to the same as your vhost.
    Example: Vhost url = explore.pixalink.io.test, APP_URL = https://explore.pixalink.io.test

### Prevent Mistake sending mail whatsapp

1. Open the `.env` file
2. Change the `QUEUE_CONNECTION=database`
3. Some configuration for mail
4. Go to mailtrap.io and create an account
5. Go to email testing -> inboxes
6. In my inbox click the setting icon on action
7. Then in the integrations click laravel 9+
8. FIll in all the information in the `.env` file

```shell
MAIL_MAILER=smtp
MAIL_HOST=smtp.mailtrap.io
MAIL_PORT=2525
MAIL_USERNAME=<username>
MAIL_PASSWORD=<password>
```

### Github Branching

1. Create a new branch from **dev** branch
2. Name the branch with the feature name
3. After finish the feature, create a pull request to **dev** branch
4. Assign the pull request to CCK
5. CCK will review the code and merge it to **dev** branch

### Testing

1. put the code below curl at php.ini

```shell
  curl.cainfo="C:/xampp/cacert.pem"
```

2. put the code below on memory limit at php.ini

```shell
  memory_limit=512M
```

## Setup Complete :+1: ! Try to open your pixalink :shipit:
