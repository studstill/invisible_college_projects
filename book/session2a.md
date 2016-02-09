# Robot Heart, Session 2A

Bonus Session!

Friday, February 5th, 7pm PST

# Tonight's Goals

* Viewing this class's source on Git
* Using a third-party library (Picasso)
* Loading an Image by URL and by a drawable.
* Recording sound using built-in microphone

# Viewing Class Source Code on GitHub

The `robot-heart-students` repo (the one you're
reading right now) is where we'll
add code after every session. You'll see a real-time
snapshot of code.

Source code is changed and then added to Git
(and pushed to GitHub) through a series of snapshots
called *commits*.

You can view commits by clicking one of two places
on this GitHub repo website. As shown on the screenshot
below, if you click on the "10 commits" button, you'll
see a history of commits, like a stream of events or
a timeline going vertically down the page. The most
recent commit is at the top, and older commits are
beneath it, going all the way back to the repo's
creation.

![Commit history](images/github-commits.png)

You can view individual commits by clicking
on the hash number (the 7 seemingly random hexadecimal
characters) that uniquely identify each commit.
When you view a commit, you can tell exactly which
files are changed, and which lines within those files.

Green means the lines were added.
Red means the lines were removed.

This is known as a *diff*, or a difference, in source
code. All commits are just diffs and can be represented
as lines added and removed.

Now you'll know exactly which lines change in every
session, and by watching the screenrecording or taking
notes, you'll know why! (knowing == half the battle)

# Including Picasso, a 3rd-party library

Often we will need functionality that's not
included in the Android platform. Many other
companies and libraries exist for your development
pleasure, and you can browse them at
[jCenter](https://bintray.com/bintray/jcenter)

Even more important, every modern development
platform has an online package repository and management
system. This lets you build a community and an
ecosystem, where developers like you can share code
with the rest of the world. Linux has apt-get and yum,
Mac has Homebrew, Python has pip, Node has npm, and
now, Android (by virtue of Java) has Maven and jCenter.

These new libraries are called *dependencies*, and
you include them in your

# Loading an Image by URL

Before we already loaded an image from the
built-in camera. There are a few other ways
to include an image in your app.

Way No. 1 is by including a static image as a drawable
resource. This is great for your app's artwork
and logo, things that are unlikely to change and
are considered essential to your app, especially
when it first loads up and when it doesn't have
internet access.

Way No. 2 is by loading the image over the internet
using Picasso.

# Recording sound
