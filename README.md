# TP_Android_HafsaMetmari
 TP_Android_HafsaMetmari

## Project Overview
NovaTech is an Android e-commerce app developed in Kotlin, focused on selling tech products. It combines ergonomics, performance, and simplicity to offer users a smooth shopping experience and provides the admin with an intuitive management interface
## Client Features
### Sign Up & Login: Secure forms to create an account or log in.
 #### Login:
 
 ![image](https://github.com/user-attachments/assets/89099ca4-6b29-467b-91c1-5c5d04e4ab7f)

 #### signup:
 
 ![image](https://github.com/user-attachments/assets/0dbaffb2-7eb3-48d1-8ff5-087144ec65bd)

### Client Dashboard
### Home: Displays a list of available products with image, name, and price.

![image](https://github.com/user-attachments/assets/afd5d08e-e195-49d4-916b-bee318bec858)

#### Filtering: Filter products by category (e.g., laptops, accessories).

![image](https://github.com/user-attachments/assets/1d2e60bb-aba9-41a9-9051-55561fea418f)

![image](https://github.com/user-attachments/assets/10734e49-5bef-48f1-b7da-180adf60f158)

#### Product Details: Page showing complete product information.

![image](https://github.com/user-attachments/assets/0023d700-6748-47d8-b4c1-c2935672aa00)

![image](https://github.com/user-attachments/assets/309f3871-8027-4cd8-b257-8ee0ead41935)

#### Add to Cart: Option to add a product to the shopping cart.

![image](https://github.com/user-attachments/assets/b0fe6a09-c7ab-4c6c-86af-4750516789d1)

#### Quantity Adjustment: Ability to decrease the quantity after adding to the cart.

![image](https://github.com/user-attachments/assets/415c7c70-dc6e-4050-b7b3-250bdbe552f6)

![image](https://github.com/user-attachments/assets/8ca511aa-8303-4c30-9b36-7790646f55be)

#### Cart Items: List of added products with their quantities.

![image](https://github.com/user-attachments/assets/71385a86-cf51-4940-adb8-b8f1277b3e6e)

#### Order Confirmation: Checkout Form to confirm the order and enter necessary delivery details.

![image](https://github.com/user-attachments/assets/d781c25e-e270-46b8-8306-8f37d9e990a8)

![image](https://github.com/user-attachments/assets/d2dad3d9-3ade-48ea-8338-9def0759fc38)

#### Order Tracking: orders with their status (in progress, delivered, cancelled, etc.).

![image](https://github.com/user-attachments/assets/0ee6ab0f-44d9-498d-b36c-dc3d0c746cb8)

#### Client Profile: Edit Information: Ability to update personal details and change password.

![image](https://github.com/user-attachments/assets/5947ee48-4b45-422b-8ac7-c8b6c8da1fdd)

![image](https://github.com/user-attachments/assets/075688c2-5dfc-44c1-a85a-5c240f8989fb)

#### Logout:Returns the user to the login page.

![image](https://github.com/user-attachments/assets/c445cee3-63fc-4201-99dc-11ca27517e03)

### Administrator Features
#### Sign Up 

![image](https://github.com/user-attachments/assets/862a201b-c8ac-465a-9ad9-c35cccc531d2)

#### Login

![image](https://github.com/user-attachments/assets/e35ca83a-f702-4fd2-ba46-c4c5524b7a95)

#### Admin Dashboard

![image](https://github.com/user-attachments/assets/c31b2067-b64b-4c14-bc00-abe73c4c3782)

#### Users Management (CRUD): View and manage the list of client accounts.

![image](https://github.com/user-attachments/assets/d71de193-fd68-4526-8f21-89543a0257ef)

#### Create & Edit: Add or modify client accounts.

![image](https://github.com/user-attachments/assets/92cc21a9-5df5-49d6-b175-44fc1d91c214)

![image](https://github.com/user-attachments/assets/201eac21-3c8f-4726-b7c8-dbbe18bc6663)

#### Delete: Remove client accounts.

![image](https://github.com/user-attachments/assets/caeb7f71-a46f-48a1-bb2d-809b920480e6)

#### Order Overview: View all client orders with status updates (prepared, shipped, delivered...).

![image](https://github.com/user-attachments/assets/210c965b-8d1a-454c-8ccd-362cb28fd3ef)

![image](https://github.com/user-attachments/assets/5d97fd9b-2d50-488f-bc8c-0115dcc73b92)

#### Product Management: Add, edit, and delete products available in the store.

![image](https://github.com/user-attachments/assets/f9e584f4-99a0-4068-9359-fe8a30ebc305)

#### Administrator Profile :Modify personal details and change password.

![image](https://github.com/user-attachments/assets/9f9fe691-4c37-4cff-b0ed-3d42df2e2f5a)

#### Logout:

![image](https://github.com/user-attachments/assets/141e3e2e-1214-46f0-ac49-a98da2573058)

## Dependencies

To manage navigation between different screens in the app, the following dependency has been added to the `build.gradle` file:

```gradle
implementation(libs.androidx.navigation.compose)




