# DeepDish

A simple architecture for updating and rendering a 2D game in Java. Features:

-   Painter thread that renders the scene at a configurable FPS rate
-   Animator thread that updates the scene at a configurable UPS rate
-   Double buffered drawing is independent of display. Thus the animation can be drawn to a Swing frame, an applet, or anything else that can be drawn to at the configured frame rate.
-   Viewport provides a window into the scene using standard cartesian coordinate system with double precision for smoother updating of animations than a pixelated approach. 
-   Having the updating, animation, and second buffering each in their own threads allows any one thread to temporarily fall behind when resources are scarce without greatly affecting the schedule of the others.

This library is used for both my Accordion Solitaire game and team DeepDish's Brawl card game.
