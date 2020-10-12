Running locally
===============

This project uses the Maven Cargo plugin to run the CMS and site locally in Tomcat.
From the project root folder, execute:

    mvn clean verify
    mvn -P cargo.run

Access the CMS at http://localhost:8080/cms, and the site at http://localhost:8080/site

Logs are located in target/tomcat9x/logs

NOTE: You can change the SMTP connection information in conf/context.xml. (The default target is localhost:2525.)
      Also, you should run an SMTP server to test all form examples sending e-mails on form submission.
      If you don't have any accessible SMTP server for testing purpose, see the following section.


Running an SMTP Server for local testing
========================================
It is very handy to be able to run an SMTP server locally to test with if you don't have access to a full mail server.
If you've got Python installed, then you can do this really easily.
Assuming you want to open the SMTP Server at port, 2525, you can run the following:

    python -m smtpd -n -c DebuggingServer localhost:2525

When the app sends an e-mail, you should be able to see it scroll by on your terminal.

If you want to open SMTP server socket at a different port (e.g, 25) instead, then you can put port number argument like the following:

    sudo python -m smtpd -n -c DebuggingServer localhost:25

On Windows, you have to open a command prompt *as administrator* to be able to open a port. The command is:  
   
    C:\> python -m smtpd -n -c DebuggingServer 0.0.0.0:25

WARNING: This is only for local testing purpose, dumping the message in console, having no knowledge on how to handle the messages.

If you don't have Python installed, download Python installer package from the following site
and install it on your system.
  - http://www.python.org/download/


Distribute source to Maven enterprise repository
================================================

To deploy the complete source tree as zip file in maven2-enterprise:

    mvn -Pdist-src deploy

Building distribution
=====================

To build a Tomcat distribution tarball containing only deployable artifacts:

    mvn clean install
    mvn -P dist

See also src/main/assembly/distribution.xml if you need to customize the distribution.


Using JRebel
============

Set the environment variable REBEL_HOME to the directory containing jrebel.jar.

Build with:

    mvn clean install -Djrebel

Start with:

    mvn -P cargo.run -Djrebel


Best Practice for development
=============================

Use the option -Drepo.path=/some/path/to/repository during start up. This will avoid
your repository to be cleared when you do a mvn clean.

For example start your project with:

    mvn -P cargo.run -Drepo.path=/home/usr/tmp/repo

or with jrebel:

    mvn -P cargo.run -Drepo.path=/home/usr/tmp/repo -Djrebel


Hot deploy
==========

To hot deploy, redeploy or undeploy the CMS or site:

    cd cms (or site)
    mvn cargo:redeploy (or cargo:undeploy, or cargo:deploy)


Automatic Export
================

To have your repository changes automatically exported to filesystem during local development, log into
http://localhost:8080/cms/console and press the "Enable Auto Export" button at the top right. To set this
as the default for your project edit the file
./bootstrap/configuration/src/main/resources/configuration/modules/autoexport-module.xml


Monitoring with JMX Console
===========================
You may run the following command:

    jconsole

Now open the local process org.apache.catalina.startup.Bootstrap start
# project-eforms
