//
//  ViewController.h
//  iDecide
//
//  Created by Gerardo Ramirez on 11/17/11.
//  Copyright (c) 2011 Ricoh America Corps. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController {
    UILabel *decisionText_;
}
@property (retain, nonatomic) IBOutlet UILabel *decisionText;

-(IBAction)buttonPressed:(id)
sender;

@end
