using Android.App;
using Android.Widget;
using Android.OS;

namespace yeticoach
{
    [Activity(Label = "SignIn", MainLauncher = true, Icon = "@drawable/icon")]
    public class SignInActivity : Activity
    {
        protected override void OnCreate(Bundle bundle)
        {
            base.OnCreate(bundle);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.SignIn);
        }
    }
}