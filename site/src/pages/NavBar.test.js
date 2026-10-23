import React from 'react';
import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import NavBar from './NavBar';
import { BrowserRouter } from 'react-router-dom';

const mockedNavigate = jest.fn();

jest.mock('react-router-dom', () => ({
  ...jest.requireActual('react-router-dom'),
  useNavigate: () => mockedNavigate,
}));

describe('NavBar', () => {
  beforeEach(() => {
    mockedNavigate.mockClear();
  });

  test('renders NavBar component with navigation links', () => {
    render(
      <BrowserRouter>
        <NavBar />
      </BrowserRouter>
    );

    expect(screen.getByText(/team 36: let's go camping!/i)).toBeInTheDocument();

    expect(screen.getByText(/login/i)).toBeInTheDocument();
    expect(screen.getByText(/search/i)).toBeInTheDocument();
    expect(screen.getByText(/register/i)).toBeInTheDocument();
  });

  test('navigates to Login when login link is clicked', () => {
    render(
      <BrowserRouter>
        <NavBar />
      </BrowserRouter>
    );

    const loginLink = screen.getByText(/login/i);
    userEvent.click(loginLink);
    expect(mockedNavigate).toHaveBeenCalledWith('/Login');
  });

  test('navigates to SearchPage when search link is clicked', () => {
    render(
      <BrowserRouter>
        <NavBar />
      </BrowserRouter>
    );

    const searchLink = screen.getByText(/search/i);
    userEvent.click(searchLink);
    expect(mockedNavigate).toHaveBeenCalledWith('/SearchPage');
  });

  test('navigates to UserRegistration when register link is clicked', () => {
    render(
      <BrowserRouter>
        <NavBar />
      </BrowserRouter>
    );

    const registerLink = screen.getByText(/register/i);
    userEvent.click(registerLink);
    expect(mockedNavigate).toHaveBeenCalledWith('/UserRegistration');
  });
});