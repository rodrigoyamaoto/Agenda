package com.example.rodrigo_yoshida.agenda.Model;

import java.io.Serializable;


public class Contact implements Serializable
{
    private Long id;
    private String pathPhoto;
    private String name;
    private String organization;
    private String telephone;
    private String typeTelephone;
    private String email;
    private String address;

    public Long getId()
    {
        return id;
    }

    public String getPathPhoto()
    {
        return pathPhoto;
    }

    public void setPathPhoto(String pathPhoto)
    {
        this.pathPhoto = pathPhoto;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization(String organization)
    {
        this.organization = organization;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTypeTelephone()
    {
        return typeTelephone;
    }

    public void setTypeTelephone(String typeTelephone)
    {
        this.typeTelephone = typeTelephone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
}
