# MUMSched

### How to setup

* Copy `src/main/resources/application.properties-template` into `src/main/resources/application.properties` and change the the
parameters accordingly

* To set the initial username and password, copy `src/main/resources/mumsched.properties-template` into `src/main/resources/mumsched.properties` and change the email and password.
The email has to be a valid email, but not a real email. The password will be encrypted, so case is important

* All the internationalization messages go here: `resources/i18n/*` then the different folders like this
   * `entitylables/` this will have all the form fields and entity related labelling
   * `sitelabels/` this is for site wide menus and text
   * `validation/` for all validation related messages.

      Remember, if you add any file other than those available here, you have to likewise update `com.mumscheduler.app.config.MUMSchedMVCConfig.java`. Watch! The location of the configs may change in the future. :(