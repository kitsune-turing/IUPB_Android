# EasyAuth

**SEIJAKU** is an Android application designed for...

## Features

- **User Registration**: Allows users to create an account with full name, email, phone number, and password.
- **User Login**: Secure login with password visibility toggle and Google sign-in.
- **Password Management**: Password visibility toggle for better user experience.
- **Account Confirmation**: Displays account details for user confirmation before finalizing registration.

## Installation

### Clone the Repository

You can clone the repository using Git from the following branch:

```bash
git clone -b first-delivery https://github.com/kitsune-turing/IUPB_Android.git
```

## Download First Delivery

If cloning the repository is not possible, you can download the initial delivery from the following link: [Download First Delivery (RAR)](https://drive.google.com/file/d/16U8JRTRILJmjkKZACWjZMXO7zt0kZK3z/view?usp=sharing)

## Set Up the Project

1. **Open the Project**: Open Android Studio and import the project.
2. **Sync Gradle**: Ensure Gradle files are synchronized.
3. **Run the Application**: Run the application on an emulator or physical device.

## Project Structure

- **/app/src/main/java/com/kitsune/android/u/auth**: Contains Java/Kotlin source code.
  - **Login.java**: Activity for user login.
  - **Register.java**: Activity for user registration.
  - **ConfirmationActivity.java**: Activity for displaying registration confirmation.
  - **utils/PasswordUtils.java**: Utility class for password management.
  - **utils/ValidationUtils.java**: Utility class for input validation.
    
- **/app/src/main/res/layout**: Contains XML layout files.
  - **activity_main.xml**: Layout for login screen.
  - **activity_register.xml**: Layout for registration screen.
  - **activity_confirmation.xml**: Layout for confirmation dialog.
    
- **/app/src/main/res/values**: Contains resource files.
  - **strings.xml**: Defines string resources.
  - **colors.xml**: Defines color resources.
  - **styles.xml**: Defines theme styles.

## Dependencies

- **Material Components**: Used for modern UI elements.
- **AndroidX Libraries**: Provides backward compatibility and additional features.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request on GitHub. Follow the standard Git workflow and ensure code quality and documentation.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For questions or feedback, please contact us at your-email@example.com.

---

### Notes:
- Make sure to replace `tobongoomez809@gmail.com` with your actual email address.
- Review and adjust any other contact information as needed.
