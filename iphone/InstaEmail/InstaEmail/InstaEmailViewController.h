//
//  InstaEmailViewController.h
//  InstaEmail
//
//  Created by Gerardo Ramirez on 1/2/12.
//  Copyright (c) 2012 Ricoh America Corps. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MessageUI/MFMailComposeViewController.h>

@interface InstaEmailViewController : UIViewController
//
// assigning both protocols
<UIPickerViewDataSource, UIPickerViewDelegate, MFMailComposeViewControllerDelegate> {
    UIPickerView *emailPicker_;
    NSArray* activities_;
    NSArray* feelings_;
    
}

@property (nonatomic, retain) IBOutlet UIPickerView *emailPicker;
- (IBAction)sendButtonTapped:(id)sender;

@end
