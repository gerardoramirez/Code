//
//  InstaEmailViewController.m
//  InstaEmail
//
//  Created by Gerardo Ramirez on 1/2/12.
//  Copyright (c) 2012 Ricoh America Corps. All rights reserved.
//

#import "InstaEmailViewController.h"

@implementation InstaEmailViewController
@synthesize emailPicker=emailPicker_;


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Release any cached data, images, etc that aren't in use.
}

#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];

    activities_ = [[NSArray alloc] initWithObjects:@"sleeping",@"eating", @"working", @"thinking", @"crying", @"begging", @"leaving", @"shopping", @"hello worlding", nil];
    
    feelings_ = [[NSArray alloc] initWithObjects:@"awesome", @"sad", @"happy", @"ambivalent", @"nauseous", @"psyched", @"confused", @"hopeful", @"anxious", 
                  nil];
}

//
// The #pragma notation is for Xcode; it helps break up the code, but
// doesn't provide any logic
#pragma mark -
#pragma mark Picker Datasource Protocol

- (NSInteger) numberOfComponentsInPickerView:(UIPickerView *) pickerView {
    return 2;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    if(component == 0) {
        return [activities_ count];
    }
    else {
        return [feelings_ count];
    }
}

#pragma mark -
#pragma mark Picker Delegate Protocol
- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    if (component == 0) {
        return [activities_ objectAtIndex:row];
    }
    else {
        return [feelings_ objectAtIndex:row];
    }
    return nil;
}

#pragma mark -
#pragma mark Actions
- (IBAction)sendButtonTapped:(id)sender {
    NSString* theMessage = [NSString stringWithFormat:@"I'm %@ and feeling %@ about it.", [activities_ objectAtIndex:[emailPicker_ selectedRowInComponent:0]],
                            [feelings_ objectAtIndex:[emailPicker_ selectedRowInComponent:1]]];
    NSLog(@"%@", theMessage);
    
    if([MFMailComposeViewController canSendMail]) {
        
        MFMailComposeViewController* mailController = [[MFMailComposeViewController alloc] init];
        mailController.mailComposeDelegate = self;
        [mailController setSubject:@"Hello Sweetie!"];
        [mailController setMessageBody:theMessage isHTML:NO];
        [self presentModalViewController:mailController animated:YES];
        [mailController release];
    }
    else {
        NSLog(@"%@", @"Sorry, you need to setup mail first!");
    }
    
    NSLog(@"Email button tapped!"); 
}

#pragma mark -
#pragma mark Mail composer delegate method
- (void) mailComposeController:(MFMailComposeViewController*) controller didFinishWithResult:(MFMailComposeResult)result error:(NSError *)error;
{
    [self dismissModalViewControllerAnimated:YES];
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
}

- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
}

- (void)viewWillDisappear:(BOOL)animated
{
	[super viewWillDisappear:animated];
}

- (void)viewDidDisappear:(BOOL)animated
{
	[super viewDidDisappear:animated];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    // Return YES for supported orientations
    return (interfaceOrientation != UIInterfaceOrientationPortraitUpsideDown);
}

- (void) dealloc {
    [activities_ release];
    [feelings_ release];
    [emailPicker_ release];
    [super dealloc];
}

@end
