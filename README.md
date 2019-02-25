# MVVM - Model View ViewModel

#### Setup :
```gradle
app/build.gradle
android {
    dataBinding {
        enabled = true
    }
 }  
```
#### Basic Layout Structure :

```xml
<layout>
  <data>
       <variable
            name="user"
            type="com.example.mvvmhelloworldexample.model.User" />
    </data>
 
    <LinearLayout>
       <!-- YOUR LAYOUT HERE -->
    </LinearLayout>
</layout>
```
#### Calling from class :

```java
ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
```

**Note:** Binding class will generate with your xml layout prefix 

**Example:** *activity_main.xml -> ActivityMainBinding.java*

#### Print value in view :
```xml
<TextView
    android:layout_width="wrap_content"
    android:textSize="25sp"
    android:layout_height="wrap_content"
    android:text="@{user.email}" />
```
**Note:** For Real time update data you should declare [notifyPropertyChanged(BR.name)]() in setter method And apply [@Bindable]() on your getter method

#### Call a mehod from layout file :
```xml
<TextView
	android:layout_width="wrap_content"
	android:textSize="30sp"
	android:layout_height="wrap_content"
	android:text="@{BindingUtils.capitalize(user.name)}" />
```
**Note:** You Should import class and method must be static

#### Import class in Layout file :
```xml
<data>
	<import type="com.example.mvvmhelloworldexample.BindingUtils" />
</data>
```
#### Call Custom Listener method :
```xml
<Button
    android:id="@+id/btn"
    android:text="Click"
    android:onClick="@{handlers::onButtonClicked}"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />
 ```
 ##### Call with param -
 ```xml
 <Button
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:onClick="@{(v) -> handlers.onButtonClickWithParam(v, user)}"
     android:text="CLICK WITH PARAM" />
 ```
 **Note:** *You should declare variable in data section*
 
 #### Image Binding :
 *Model class:*
 ```java
 private String profileImage;
 
 public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @BindingAdapter({"android:profileImage"})
    public static void loadImage(ImageView view, String imageUrl) {
        //If you use picasso
        Picasso.get().load(imageUrl).into(view);
    }
```
*From layout:*
```xml
<ImageView
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:layout_marginTop="5dp"
    android:profileImage="@{user.profileImage}" />
```
*Calling for uses:*
```java
private void imageBinding(){
    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    User user = new User();
    user.setProfileImage("https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg");
    binding.setUser(user);
}
```

