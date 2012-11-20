//
//  main.m
//  Bool Party
//
//  Created by Gerardo Ramirez on 11/21/11.
//  Copyright (c) 2011 Ricoh America Corps. All rights reserved.
//

#import <Foundation/Foundation.h>


// return NO if the two integers have the same value, YES otherwise
BOOL areIntsDifferent(int thing1, int thing2)
{
    if(thing1 == thing2) {
        return (NO);
    } else {
        return (YES);
    }
} // areIntsDifferent


// given a YES value, return the human-readable
// string "YES".  Otherwise return "NO"
NSString *boolString (BOOL yesNo)
{
    if(yesNo == NO) {
        return (@"NO");
    } else {
        return (@"YES");
    }
}

void forLoopSample ()
{
    int count = 100;
    
    NSLog(@"the numbers 1 to %d: ", count);
    int i;
    for(i = 1; i <= count; i++) {
        NSLog(@"%d", i);
    }
}

void wordOLenghtSample() {
    const char *words[4] = {"hello", "abacus",
        "hi", "game"};
    int wordCount = 4;
    
    int i;
    for(i = 0; i < wordCount; i++) {
        NSLog(@"%s is %d characters long ", 
              words[i], strlen(words[i]));
    }
    
}

void wordLengthTextFileSample() {
    FILE *wordFile = fopen("/tmp/words.txt", "r");
    
    char word[100];
    
    while(fgets(word, 100, wordFile)) {
        // strip off the trailing \n
        word[strlen(word) - 1] = '\0';
        
        NSLog(@"%s is %d characters long",
              word, strlen(word));
    }
    fclose(wordFile);
}

void boolPartySample() {
    BOOL areTheyDifferent;
    
    areTheyDifferent = areIntsDifferent(5, 5);
    
    NSLog(@"are %d and %d different? %@", 5, 5, boolString(areTheyDifferent));
    
    areTheyDifferent = areIntsDifferent(23,42);
    
    NSLog(@"are %d and %d different? %@", 23, 42, boolString(areTheyDifferent));

}

                        
int main (int argc, const char * argv[])
{
        
        // insert code here...
        NSLog(@"Hello, World!");
        
                
    //forLoopSample();
    //wordOLenghtSample();
    wordLengthTextFileSample();
    
    return 0;
}

