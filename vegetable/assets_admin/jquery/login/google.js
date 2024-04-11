
document.addEventListener("DOMContentLoaded", function () {
  const extractResponseData = (response) => {
    const responseObject = JSON.parse(response);

    const base64Data = responseObject.base64Data
      .replace(/-/g, "+")
      .replace(/_/g, "/");
    const decodedData = atob(base64Data);
    const binaryData = decodeURIComponent(escape(decodedData));
    console.log(binaryData);

    // Parse the binaryData JSON string
    const jsonData = JSON.parse(binaryData);
    const email = jsonData.email;
    const name = jsonData.name;
    localStorage.setItem("username",name);
  };

  const getAuthResponse = () => {
    const urlParams = new URLSearchParams(window.location.search);
    const response = urlParams.get("response");
    if (response) {
      extractResponseData(response);
    }
  };

  getAuthResponse();
});
