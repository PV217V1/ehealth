package soa.ehealth.certificates.services;

import soa.ehealth.certificates.entities.Certificate;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class CertificateService {

    @Transactional
    public Certificate createCertificate(Certificate certificate) {
        certificate.persist();
        return certificate;
    }

    @Transactional
    public Certificate updateCertificate(Long id, Certificate update) {
        Certificate certificate = Certificate.findById(id);
         if (certificate == null) {
             throw new NotFoundException("Certificate id " + id + " not found");
         }

         certificate.update(update);

        return certificate;
    }

    @Transactional
    public boolean deleteCertificate(Long id) {
        return Certificate.deleteById(id);
    }
}
