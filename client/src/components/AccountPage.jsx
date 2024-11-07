import { Globe, Heart, MapPin } from 'lucide-react'
import React, { useContext, useEffect, useState } from 'react'

import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Textarea } from "@/components/ui/textarea"
import { AccountContext } from "../contexts/AccountContext.jsx"
import Header from "./Header.jsx"


const AccountPage = () => {
  const {
    accountState,
    setFirstName,
    setLastName,
    setPhoneNumber,
    setEmail,
    setCity,
    setCountry,
    setBio,
    setPlaceTravelled,
    setWishlist,
    setTravelQuote
  } = useContext(AccountContext);

  const [countries, setCountries] = useState([]);

  let placeTravelled = new Set([accountState.placeTravelled]);

  useEffect(() => {
    fetch('https://restcountries.com/v3.1/all?fields=name')
      .then(response => response.json())
      .then(data => {
        const sortedCountries = data
          .map(country => country.name.common)
          .sort((a, b) => a.localeCompare(b))
        setCountries(sortedCountries)
      })
      .catch(error => console.error('Error fetching countries:', error))
  }, [])
  




  const handleInputChange = (e, setter) => {
    const { value } = e.target
    setter(value);
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    console.log('Updated Info: ', accountState)
  }

  const handleCountrySelect = (list, country) => {
    const currentList = accountState[list];
    const updatedList = currentList.includes(country)
      ? currentList.filter(c => c !== country)
      : [...currentList, country];
    
    if (list === 'placeTravelled') {
      setPlaceTravelled(updatedList);
    } else if (list === 'wishlist') {
      setWishlist(updatedList);
    }
  }

  return (
    <div className="min-h-screen bg-black text-white">
      <header className="flex h-16 items-center justify-between bg-zinc-900">
        <h1 className="text-xl font-bold header-navbar">Travelopedia</h1>
        
        <Header></Header>
      </header>
      <div className="container mx-auto px-4 py-8">
        <div className="grid grid-cols-1 md:grid-cols-[300px_1fr] gap-8">
          <aside className="space-y-6">
            <Card className="bg-zinc-900 border-zinc-800">
              <CardContent className="p-6 flex flex-col items-center">
                <Avatar className="h-24 w-24 mb-4">
                  <AvatarImage src="/placeholder.svg?height=96&width=96" alt={`${accountState.firstName} ${accountState.lastName}`} />
                  <AvatarFallback className="bg-zinc-800 text-xl">{accountState.firstName[0]}{accountState.lastName[0]}</AvatarFallback>
                </Avatar>
                <h2 className="text-xl font-bold">{accountState.firstName} {accountState.lastName}</h2>
                <p className="text-sm text-zinc-400 mb-4">Software Developer</p>
                <div className="w-full space-y-2 mb-4">
                  <div className="flex items-center gap-2 text-sm">
                    <MapPin className="h-4 w-4" />
                    {accountState.city}, {accountState.country}
                  </div>
                  <div className="flex items-center gap-2 text-sm">
                    <Globe className="h-4 w-4" />
                    {accountState.placeTravelled.length} places travelled
                  </div>
                  <div className="flex items-center gap-2 text-sm">
                    <Heart className="h-4 w-4" />
                    {accountState.wishlist.length} places in wishlist
                  </div>
                </div>
                <Button className="w-full bg-blue-600 hover:bg-blue-700">View Public Profile</Button>
              </CardContent>
            </Card>
            <Card className="bg-zinc-900 border-zinc-800">
              <CardHeader>
                <CardTitle>Travel Stats</CardTitle>
              </CardHeader>
              <CardContent className="space-y-2">
                <div className="flex justify-between">
                  <span>Places Travelled:</span>
                  <span>{accountState.placeTravelled.length}</span>
                </div>
                <div className="flex justify-between">
                  <span>Wishlist Places:</span>
                  <span>{accountState.wishlist.length}</span>
                </div>
              </CardContent>
            </Card>
          </aside>
          <main>
            <form onSubmit={handleSubmit} className="space-y-6">
              <Card className="bg-zinc-900 border-zinc-800">
                <CardHeader>
                  <CardTitle>Personal Information</CardTitle>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="grid grid-cols-2 gap-4">
                    <div className="space-y-2">
                      <Label htmlFor="firstName">First Name</Label>
                      <Input
                        id="firstName"
                        name="firstName"
                        value={accountState.firstName}
                        onChange={(e) => handleInputChange(e, setFirstName)}
                        className="bg-zinc-800 border-zinc-700"
                      />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="lastName">Last Name</Label>
                      <Input
                        id="lastName"
                        name="lastName"
                        value={accountState.lastName}
                        onChange={(e) => handleInputChange(e, setLastName)}
                        className="bg-zinc-800 border-zinc-700"
                      />
                    </div>
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="email">Email Address</Label>
                    <Input
                      id="email"
                      name="email"
                      type="email"
                      value={accountState.email}
                      onChange={(e) => handleInputChange(e, setEmail)}
                      className="bg-zinc-800 border-zinc-700"
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="phoneNumber">Phone Number</Label>
                    <Input
                      id="phoneNumber"
                      name="phoneNumber"
                      type="tel"
                      value={accountState.phoneNumber}
                      onChange={(e) => handleInputChange(e, setPhoneNumber)}
                      className="bg-zinc-800 border-zinc-700"
                    />
                  </div>
                  <div className="grid grid-cols-2 gap-4">
                    <div className="space-y-2">
                      <Label htmlFor="city">City</Label>
                      <Input
                        id="city"
                        name="city"
                        value={accountState.city}
                        onChange={(e) => handleInputChange(e, setCity)}
                        className="bg-zinc-800 border-zinc-700"
                      />
                    </div>
                    <div className="space-y-2">
                      <Label htmlFor="country">Country</Label>
                      <Select 
                        name="country" 
                        value={accountState.country} 
                        onValueChange={(value) => setCountry(value)}
                      >
                        <SelectTrigger className="bg-zinc-800 border-zinc-700">
                          <SelectValue placeholder="Select a country" />
                        </SelectTrigger>
                        <SelectContent className="bg-zinc-800 border-zinc-700 text-white max-h-[300px]" position="popper" side="bottom" sideOffset={5}>
                          {countries.map((country) => (
                            <SelectItem key={country} value={country}>
                              {country}
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                  </div>
                </CardContent>
              </Card>
              <Card className="bg-zinc-900 border-zinc-800">
                <CardHeader>
                  <CardTitle>Travel Information</CardTitle>
                </CardHeader>
                <CardContent className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="bio">Bio</Label>
                    <Textarea
                      id="bio"
                      name="bio"
                      value={accountState.bio}
                      onChange={(e) => handleInputChange(e, setBio)}
                      className="bg-zinc-800 border-zinc-700"
                      rows={3}
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="travelQuote">Favorite Travel Quote</Label>
                    <Input
                      id="travelQuote"
                      name="travelQuote"
                      value={accountState.travelQuote}
                      onChange={(e) => handleInputChange(e, setTravelQuote)}
                      className="bg-zinc-800 border-zinc-700"
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="placeTravelled">Places Travelled</Label>
                    <Select
                      name="placeTravelled"
                      value={accountState.placeTravelled}
                      onValueChange={(value)=>{
                        
                        if (value && !accountState.placeTravelled.includes(value)) {
                          setPlaceTravelled([...accountState.placeTravelled, value]);
                        }
                      }}
                      multiple
                    >
                      <SelectTrigger className="bg-zinc-800 border-zinc-700">
                        <SelectValue placeholder="Select countries" />
                      </SelectTrigger>
                      <SelectContent className="bg-zinc-800 border-zinc-700 text-white max-h-[300px]" position="popper" side="bottom" sideOffset={5}>
                        {countries.map((country) => (
                          <SelectItem key={country} value={country}>
                            {country}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <div className="flex flex-wrap gap-2 mt-2">
                      {accountState.placeTravelled.length!==0 ? accountState.placeTravelled.map((place) => (
                        <div key={place} className="bg-zinc-800 text-white px-3 py-1 rounded-full text-sm flex items-center">
                          {place}
                          <button
                            onClick={() => {
                            let filteredValue=[];
                            for(let i = 0; i < accountState.placeTravelled.length; i++) {
                              if (accountState.placeTravelled[i] !== place) {
                                filteredValue.push(accountState.placeTravelled[i]);
                              }
                            }
                            console.log('filteredValue:', filteredValue);
                            setPlaceTravelled(accountState.placeTravelled.filter(c => c !== place));
                            console.log('place:', place, accountState.placeTravelled.filter(c => c !== place));
                            }}
                            className="ml-2 text-red-500 hover:text-red-700"
                            type="button"
                          >
                            ×
                          </button>
                        </div>
                      )): null}
                    </div>
                    {console.log('accountState.placeTravelled:', accountState.placeTravelled)}
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="wishlist">Travel Wishlist</Label>
                    <Select
                      name="wishlist"
                      value={accountState.wishlist}
                      onValueChange={(value)=>{
                        if (value && !accountState.wishlist.includes(value)) {
                          setWishlist([...accountState.wishlist, value]);
                        }
                      }}
                      multiple
                    >
                      <SelectTrigger className="bg-zinc-800 border-zinc-700">
                        <SelectValue placeholder="Select countries" />
                      </SelectTrigger>
                      <SelectContent className="bg-zinc-800 border-zinc-700 text-white max-h-[300px]" position="popper" side="bottom" sideOffset={5}>
                        {countries.map((country) => (
                          <SelectItem key={country} value={country}>
                            {country}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <div className="flex flex-wrap gap-2 mt-2">
                      {accountState.wishlist.map((place) => (
                        <div key={place} className="bg-zinc-800 text-white px-3 py-1 rounded-full text-sm flex items-center">
                          {place}
                          <button
                            onClick={() => setWishlist(accountState.wishlist.filter(c => c !== place))}
                            className="ml-2 text-red-500 hover:text-red-700"
                            type="button"
                          >
                            ×
                          </button>
                        </div>
                      ))}
                    </div>
                  </div>
                </CardContent>
              </Card>
              <Button type="submit" className="w-full bg-blue-600 hover:bg-blue-700">
                Update Profile
              </Button>
            </form>
          </main>
        </div>
        <style jsx global>{`
          .select-content {
            z-index: 1000 !important;
          }
        `}</style>
      </div>
    </div>
  )
}

export default AccountPage