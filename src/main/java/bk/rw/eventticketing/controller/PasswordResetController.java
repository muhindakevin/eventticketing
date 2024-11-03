// package bk.rw.eventticketing.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import bk.rw.eventticketing.model.User;
// import bk.rw.eventticketing.service.UserService;

// @RestController
// @RequestMapping("/api/password")
// public class PasswordResetController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/forgot")
//     public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) {
//         try {
//             userService.forgotPassword(email);
//             return ResponseEntity.ok().body("Password reset email sent");
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body("Error: " + e.getMessage());
//         }
//     }

//     @PostMapping("/reset")
//     public ResponseEntity<?> resetPassword(@RequestParam("token") String token,
//                                            @RequestParam("password") String password) {
//         String result = userService.validatePasswordResetToken(token);
//         if (result != null) {
//             return ResponseEntity.badRequest().body("Error: " + result);
//         }

//         User user = userService.getUserByPasswordResetToken(token);  // Assuming this method exists in UserService
//         if (user != null) {
//             userService.changeUserPassword(user, password);
//             return ResponseEntity.ok().body("Password has been reset successfully");
//         } else {
//             return ResponseEntity.badRequest().body("Error: User not found");
//         }
//     }
// }
