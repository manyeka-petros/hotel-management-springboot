package com.mapoto.HotelManagement.Servi;


import com.mapoto.HotelManagement.Emails.EmailValidator;
import com.mapoto.HotelManagement.Emails.SendEmai;
import com.mapoto.HotelManagement.Entiy.AppUserRoles;
import com.mapoto.HotelManagement.Entiy.AppUsers;
import com.mapoto.HotelManagement.Entiy.Roles;
import com.mapoto.HotelManagement.Model.AppUserModels;
import com.mapoto.HotelManagement.Reposito.AppUserRepository;
import com.mapoto.HotelManagement.Reposito.RolesReposito;
import com.mapoto.HotelManagement.VerifiToken.VerificationToken;
import com.mapoto.HotelManagement.VerifiToken.VerificationTokenRepository;
import com.mapoto.HotelManagement.VerifiToken.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RolesReposito rolesReposito;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private  AppUserSer appUserSer;
    @Autowired
    private EmailValidator emailValidator;





    @Override
    public String removeUser(Long userId) {
      appUserRepository.deleteById(userId);
        return "the user with id  " + userId+ " is deleted";
    }

    @Override
    public String saveRole(Roles roles) {
        rolesReposito.save(roles);
        return "saved";
    }

    @Override
    public String registerUsers(AppUserModels appUserModels) {
        boolean isValidEmail = emailValidator.test(appUserModels.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("Email not valid");
        }

        if(appUserRepository.existsByEmail(appUserModels.getEmail())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email entered already exist");
        }
        AppUsers appUser = new AppUsers();
        appUser.setFirstname(appUserModels.getFirstname());
        appUser.setLastname(appUserModels.getLastname());
        appUser.setEmail(appUserModels.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserModels.getPassword()));
        Set<String> roleString = appUserModels.getRoles();
        Set<Roles> roles = new HashSet<>();
        if(roleString == null){
            Roles userRole = rolesReposito.findByName(AppUserRoles.ROLE_USER).orElseThrow(
                    ()-> new RuntimeException("User role not found")
            );
            roles.add(userRole);
        }
        else {
            roleString.forEach(
                    role-> {
                        switch (role){
                            case "mod":
                                Roles moderator = rolesReposito.findByName(AppUserRoles.ROLE_MODERATOR).orElseThrow(
                                        ()-> new RuntimeException("The moderator role is not found")
                                );
                                roles.add(moderator);
                                break;
                            case "admin":
                                Roles admin = rolesReposito.findByName(AppUserRoles.ROLE_ADMIN).orElseThrow(
                                        ()-> new RuntimeException("The addmi not found ")
                                );
                                roles.add(admin);
                                break;
                            default:
                                Roles userRole = rolesReposito.findByName(AppUserRoles.ROLE_USER).orElseThrow(
                                        () -> new RuntimeException("user not found")
                                );
                                roles.add(userRole);

                        }
                    }
            );
        }
        appUser.setRoles(roles);

        appUserRepository.save(appUser);
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                token, LocalDate.now(),LocalDate.now().plusDays(1),appUser
        );
        verificationTokenService.saveVerificationToken(verificationToken);
        String link = "http://localhost:8080/confirm?token="+ token;


        return "saved";
    }

    @Override
    public List<AppUsers> getAllUser() {
        return appUserRepository.findAll();
    }

    @Transactional
    public String confirmToken(String token) {
        VerificationToken verificationToken = verificationTokenService.getToken(token).orElseThrow(
                ()-> new IllegalStateException("token not available")
        );
        if(verificationToken.getConfirmedAt() != null){
            throw new IllegalStateException("token is already confirmed");
        }
        LocalDate expiredAt = verificationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDate.now())){
            throw new IllegalStateException("token is alredy expired");
        }
        verificationTokenService.setConfirmedTok(token);
        appUserSer.enableAppUser(verificationToken.getAppUser().getEmail());


        return "confirmed";


    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
