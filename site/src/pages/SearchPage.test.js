import React from 'react';
import { render, screen, fireEvent } from '@testing-library/react';
import SearchPage from './SearchPage';
import '@testing-library/jest-dom/extend-expect';

global.fetch = jest.fn(() =>
  Promise.resolve({
    json: () => Promise.resolve({ results: [] }),
  })
);

beforeEach(() => {
  fetch.mockClear();
});

describe('SearchPage', () => {
  test('renders SearchPage component', () => {
    render(<SearchPage />);

    expect(screen.getByText(/let's go camping!/i)).toBeInTheDocument();
    expect(screen.getByText(/search/i)).toBeInTheDocument();
  });

  test('allows user to select a search option and enter search parameters', () => {
    render(<SearchPage />);

    fireEvent.change(screen.getByRole('combobox'), {
      target: { value: 'activity' },
    });

    const input = screen.getByPlaceholderText('Search');
    fireEvent.change(input, { target: { value: 'hiking' } });

    expect(screen.getByDisplayValue('hiking')).toBeInTheDocument();
  });

  test('handles search button click and fetches data', async () => {
    render(<SearchPage />);

    fireEvent.change(screen.getByRole('combobox'), {
      target: { value: 'search' },
    });

    const input = screen.getByPlaceholderText('Search');
    fireEvent.change(input, { target: { value: 'national park' } });

    const searchButton = screen.getByRole('button', { name: /search/i });
    fireEvent.click(searchButton);

    expect(fetch).toHaveBeenCalledWith(expect.anything(), expect.objectContaining({
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        type: 'search',
        params: { query: 'national park' },
      }),
    }));
  });

});
