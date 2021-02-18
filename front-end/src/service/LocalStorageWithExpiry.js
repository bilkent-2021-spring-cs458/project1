const ttl = 3600000; // 1 hour

export const setLocalStorage = (key, value) => {
    const now = new Date();

    const item = {
        value: value,
        expiry: now.getTime() + ttl,
    };
    localStorage.setItem(key, JSON.stringify(item));
};

export const getLocalStorage = (key) => {
    const itemStr = localStorage.getItem(key);
    if (!itemStr) {
        return null;
    }

    const item = JSON.parse(itemStr);
    const now = new Date();
    if (now.getTime() > item.expiry) {
        localStorage.removeItem(key);
        return null;
    }
    return item.value;
};
