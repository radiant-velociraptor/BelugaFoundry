Beluga Foundry is a backend project for a virtual pet site.

When I was younger, I wanted nothing more than to make one of these, but never had the technical chops.

Now I figure . . . why not?

I have no intention of launching this project to the public, however, because I simply don't have the time needed
to run a VPS, nor do I have the inclination to go around trying to recruit people to do it for me, somehow, gratis.
For that same reason I ended up scaling way back on features fairly early into the project. With projects like these,
there's always another cool feature to add to extend development time even longer.

The frontend half of Beluga Foundry will appear closer to the end of the year or early next year, depending on what
other projects I have brewing and how busy I am at work. The intention is to return JSON from the controllers and have
something like Angular consume it to build pages.

Beluga Foundry is 100% a Java project (most pet sites run off of PHP, it seems), using Spring for dependency injection,
Maven for builds/dependencies, and Shiro for authentication and authorization. It runs without a web.xml, so no need to
panic when you realize there isn't one to be found.

In order to deploy, build and drop the WAR into your container of choice. I prefer Tomcat.

You will need to support Java 8 in order to build and deploy Beluga Foundry.