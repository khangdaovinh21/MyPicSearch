USER GUIDE FOR MYPICSEARCH APP 

1. Opening the App and Loading Image List 
Upon launching the app, a list of images will automatically load from the server. 
If no images are displayed initially, try searching by entering a keyword into the search box and pressing 
"Search". 

2. Searching for New Images 
To search for new images, enter a keyword in the search box at the top of the screen. 
Press the "Search" button to search and display a list of new images related to the entered keyword. 

3. Viewing Image Details 
Tap on an image in the image list screen to view its details. 
The image will expand to a larger view where you can zoom in, zoom out, or pan to view details. 

4. Opening the Image Source 
In the image detail view, you can press the "Open Source" button to open the original source of the 
image in a web browser. 
This will open a new web page and display the image from its original source. 

5. Slideshow Presentation of Images 
To view a slideshow of images, navigate to the Presentation screen. 
The Presentation screen automatically presents images in a slideshow format. You can pause/resume 
the slideshow and navigate between images. 

6. Navigating and Interacting with Images 
In both the detail view and slideshow mode, use gestures such as swiping to navigate through images 
or use control buttons to interact with images. 

Notes: 
Ensure your device is connected to the internet to download and view images from the app. 
If you encounter any issues during usage, please check your internet connection and try again. 
This is a preliminary guide to help users get started and explore the Mypicsearch app effectively and 
effortlessly.

FUNCTIONALITY LIST OF MYPICSEARCH TASK 

MainActivity 
Functionality: 
• Display a list of images from an API when the application starts. 
• Allow users to enter search keywords and perform new image searches. 
• Implement pagination and load more images when scrolling to the end of the list. 
• Apply Shared Element Transition effect when switching from FullScreenImageActivity to 
  MainActivity to create a smoother experience when the user selects an image. 

FullScreenImageActivity 
Functionality: 
• Display a full-screen image from the list when a user taps on an image in MainActivity. 
• Allow users to zoom in, zoom out, and pan the image. 
• Provide a button to open the image source in a web browser. 
• Apply Shared Element Transition effect when switching from MainActivity to 
  FullScreenImageActivity to create a smoother experience when the user selects an image. 

PresentationActivity 
Functionality: 
• Display a slideshow of images automatically. 
• Allow users to control the slideshow (pause, resume) and switch between images. 
  OriginalSourceActivity 
  Functionality: 
• Display the original source of an image in a web browser when the user taps on the "Open Source" 
  button from FullScreenImageActivity. 

ZoomImageView 
Functionality: 
• Custom ImageView that supports zooming and panning of images. 
• Allow users to zoom in, zoom out, and pan the image to view details. 

ZoomActivity 
Functionality: 
• Display an image with zoom, pan functionalities similar to ZoomImageView. 
• Provide a user interface for interacting with the image. 

ImageAdapter 
Functionality: 
• Manage and display a list of images in a RecyclerView. 
• Allow updating the list of images and handle events when users click on an image. 

CustomImagePagerAdapter 
Functionality: 
• Manage and display a list of images in a ViewPager. 
• Load and display images from URLs using the Glide library. 

SpacingItemDecoration 
Functionality: 
• Add spacing between items in a RecyclerView. 
• Provide customizable spacing between items. 

SearchService 
Functionality: 
• Define API methods for searching images from the server. 
• Use Retrofit to send requests and receive responses from the server. 

MainActivity and RetrofitClient 
Functionality: 
• MainActivity is the main screen of the application. 
• RetrofitClient is a singleton object used to initialize Retrofit and configure sending and receiving 
  data from the server. 

GestureListener and ScaleListener 
Functionality: 
• GestureListener and ScaleListener are used in ZoomImageView to handle swipe and scale events 
on images, allowing users to zoom, pan, and interact with images.
