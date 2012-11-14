//
//  main.m
//  HelloWorld
//
//  Created by Gerardo Ramirez on 7/9/12.
//  Copyright (c) 2012 Ricoh America Corps. All rights reserved.
//

#import <Foundation/Foundation.h>

BOOL areIntsDifferent (int firstInt, int secondInt) {
    
    if(firstInt == secondInt) {
        return (NO);
    } else {
        return (YES);
    }
    
}

NSString *boolString (BOOL yesNo) {
    if(yesNo == NO) {
        return (@"No");
    } else {
        return (@"YES");
    }
} //boolString

int main (int argc, const char * argv[])
{

    // insert code here...
    NSLog(@"Hello, World!");
    
    NSLog(@"Bool in action:");
    BOOL areTheyDifferent;
    areTheyDifferent = areIntsDifferent(3, 4);
    
    NSLog(@"are %d and %d different? %@", 3,4,boolString(areTheyDifferent));
    
    return 0;
}

