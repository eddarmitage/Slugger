# Slugger - A Java library for creating nice URL Slugs

[![Build Status](https://travis-ci.org/eddarmitage/Slugger.svg?branch=master)](https://travis-ci.org/eddarmitage/Slugger)
[![Coverage Status](https://coveralls.io/repos/github/eddarmitage/Slugger/badge.svg?branch=master)](https://coveralls.io/github/eddarmitage/Slugger?branch=master)
[![License](https://img.shields.io/github/license/eddarmitage/Slugger.svg)](LICENSE)


## Overview
Instances of a `Slugger` object provide a way to generate _"slugs"_, that can
be used in URLs on the web. Inputs can be split into words in a variety of ways
and the length of the output can be restricted if required. Any `CharSequence`
can be used as the joining character between consecutive words. Characters that
would require escaping in a URL are removed or replaced, as appropriate.

## Getting Started
The simplest usage of `Slugger` would be something like the following:

```java
Slugger slugger = Slugger.create();
String firstTitle = slugger.sluggify("My first blog post"); // my-first-blog-post
String secondTitle = slugger.sluggify("Another post!"); // another-post
```

This creates an instance of a `Slugger` with the default configuration, which
splits the input on whitespace, removes special characters, and converts the
output to lowercase.

## Word Splitters
By default, a Slugger will split the supplied input into words based on
whitespace, although a number of alternative word splitters are provided (and
[the interface][word-splitter] is publicly exposed for extensibility).

To replace the default splitter, use the `withWordSplitter(WordSplitter ws)`
method as follows:

```java
Slugger slugger = Slugger.create().withWordSplitter(WordSplitters.camelCaseWordSplitter());
slugger.sluggify("MyFirstBlogPost"); // my-first-blog-post
```

Splitters can also be _chained_, so you can split on both whitespace and
camel-case, as follows:

```java
Slugger slugger = Slugger.create().withAdditionalWordSplitter(WordSplitters.camelCaseWordSplitter());
slugger.sluggify("The importance of toString()"); //the-importance-of-to-string
```

## Development
Slugger is built using [Gradle](https://gradle.org), and uses the 
Wrapper to achieve a consistent build experience. It can be built as follows
(including running all the tests):

```console
$ ./gradlew build
```

Alternatively to just run some of the build phases, you can use commands like
the following:

```console
$ ./gradlew test #Compiles Slugger and runs unit tests
$ ./gradlew cucumber #Compiles Slugger and runs Cucumber tests
$ ./gradlew check #Compiles Slugger and runs all tests
```

[word-splitter]: src/main/java/com/eddarmitage/slugger/WordSplitter.java
